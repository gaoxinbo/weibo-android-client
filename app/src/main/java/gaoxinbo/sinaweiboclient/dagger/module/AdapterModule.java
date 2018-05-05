package gaoxinbo.sinaweiboclient.dagger.module;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gaoxinbo.sinaweiboclient.adapter.TimelineAdapter;
import gaoxinbo.sinaweiboclient.service.retrofit.wrapper.RetrofitTimelineWrapper;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboWrapper;

@Module
public class AdapterModule {
//
//    @Singleton
//    @Provides
//    public TimelineAdapter providesTimelineAdapter(
//            Gson gson,
//            Context context,
//            RetrofitTimelineWrapper retrofitTimelineWrapper,
//            WeiboWrapper weiboWrapper) {
//        return TimelineAdapter.of(context, gson, retrofitTimelineWrapper, weiboWrapper);
//    }
}
