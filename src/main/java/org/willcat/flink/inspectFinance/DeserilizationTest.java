package org.willcat.flink.inspectFinance;

import com.google.gson.Gson;

public class DeserilizationTest {
    public static void main(String[] args) {
        String message = "{\"boxOffice\": \"39.53\", \"rank\": \"9\", \"movieName\": \"\\u72c4\\u4ec1\\u6770\\u4e4b\\u56db\\u5927\\u5929\\u738b\", \"boxPer\": \"0.36\", \"movieDay\": \"18\", \"sumBoxOffice\": \"59228.39\", \"timeStamp\": 1534149341}";
        Gson gson = new Gson();
        MovieBoxEvent event = gson.fromJson(message,MovieBoxEvent.class);
        System.out.println(event.getBoxOffice());
    }
}
