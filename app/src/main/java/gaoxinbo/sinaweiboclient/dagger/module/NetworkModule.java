package gaoxinbo.sinaweiboclient.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gaoxinbo.sinaweiboclient.service.retrofit.RetrofitTimelineService;
import gaoxinbo.sinaweiboclient.service.retrofit.wrapper.RetrofitTimelineWrapper;
import gaoxinbo.sinaweiboclient.service.retrofit.wrapper.UserTweetService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public RetrofitTimelineService providesRetrofitTimelineService() {
        String timeline_url = "https://api.weibo.com/2/statuses/";

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

    @Singleton
    @Provides
    public RetrofitTimelineWrapper providesRetrofitTimelineWrapper(RetrofitTimelineService retrofitTimelineService) {
        return new RetrofitTimelineWrapper(retrofitTimelineService);
    }

    @Singleton
    @Provides
    public UserTweetService provideUserTweetService(RetrofitTimelineService retrofitTimelineService) {
        return new UserTweetService(retrofitTimelineService);

    }
}
