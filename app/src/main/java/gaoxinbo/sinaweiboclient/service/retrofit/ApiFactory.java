package gaoxinbo.sinaweiboclient.service.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    static String timeline_url = "https://api.weibo.com/2/statuses/";

    static public RetrofitTimelineService getTimelineService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(timeline_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitTimelineService retrofitTimelineService = retrofit.create(RetrofitTimelineService.class);
        return retrofitTimelineService;
    }
}
