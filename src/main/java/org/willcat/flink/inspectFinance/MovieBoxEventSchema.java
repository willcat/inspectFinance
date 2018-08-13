package org.willcat.flink.inspectFinance;

import com.google.gson.Gson;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

/**
 * @author Administrator
 */
public class MovieBoxEventSchema implements DeserializationSchema<MovieBoxEvent> , SerializationSchema<MovieBoxEvent> {
    @Override
    public MovieBoxEvent deserialize(byte[] message) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(new String(message), MovieBoxEvent.class);
    }

    @Override
    public boolean isEndOfStream(MovieBoxEvent movieBoxEvent) {
        return false;
    }

    @Override
    public TypeInformation<MovieBoxEvent> getProducedType() {
        return TypeInformation.of(MovieBoxEvent.class);
    }

    @Override
    public byte[] serialize(MovieBoxEvent movieBoxEvent) {
        return movieBoxEvent.toString().getBytes();
    }
}
