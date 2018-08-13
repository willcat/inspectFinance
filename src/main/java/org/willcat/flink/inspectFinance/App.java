package org.willcat.flink.inspectFinance;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.util.Collector;

import java.io.IOException;
import java.time.Clock;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        // only required for Kafka 0.8
        properties.setProperty("zookeeper.connect", "localhost:2181");
        properties.setProperty("group.id", "foobar");

        FlinkKafkaConsumer011<MovieBoxEvent> myConsumer = new FlinkKafkaConsumer011<>("foobar", new MovieBoxEventSchema(), properties);
        DataStream<Tuple4<String,Long,Double,Double>> stream = env.addSource(myConsumer)
                                .keyBy(MovieBoxEvent::getMovieName)
                                .window(SlidingProcessingTimeWindows.of(Time.minutes(10), Time.minutes(1)))
                                .apply(new WindowFunction<MovieBoxEvent, Tuple4<String, Long, Double,Double>, String, TimeWindow>() {
                                    @Override
                                    public void apply(String key, TimeWindow timeWindow, Iterable<MovieBoxEvent> iterable, Collector<Tuple4<String, Long, Double, Double>> out) throws Exception {
                                        Double origin = 0d;
                                        Double last = 0d;
                                        for (MovieBoxEvent event: iterable) {
                                            if(origin == 0d) {
                                                origin = event.getBoxOffice();
                                            }
                                            last = event.getBoxOffice();
                                        }
                                        out.collect(new Tuple4<>(key,timeWindow.maxTimestamp(),(last-origin)/origin, last));
                                    }
                                });
        stream.print();
        env.execute("begin to run:");
    }
}
