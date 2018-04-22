package gaoxinbo.sinaweiboclient.application;

import android.app.Application;
import android.content.Context;

import gaoxinbo.sinaweiboclient.dagger.component.DaggerLoginActivityComponent;
import gaoxinbo.sinaweiboclient.dagger.component.DaggerSettingFragmentComponent;
import gaoxinbo.sinaweiboclient.dagger.component.DaggerTimelineFragmentComponent;
import gaoxinbo.sinaweiboclient.dagger.component.LoginActivityComponent;
import gaoxinbo.sinaweiboclient.dagger.component.SettingFragmentComponent;
import gaoxinbo.sinaweiboclient.dagger.component.TimelineFragmentComponent;
import gaoxinbo.sinaweiboclient.dagger.module.NetworkModule;
import gaoxinbo.sinaweiboclient.dagger.module.StorageModule;
import lombok.Getter;

public class WeiboApplication extends Application {
    @Getter
    private static Context instance;

    @Getter
    LoginActivityComponent loginActivityComponent;
    @Getter
    SettingFragmentComponent settingFragmentComponent;
    @Getter
    TimelineFragmentComponent timelineFragmentComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        loginActivityComponent = DaggerLoginActivityComponent
                .builder().storageModule(new StorageModule()).build();

        settingFragmentComponent = DaggerSettingFragmentComponent
                .builder().storageModule(new StorageModule()).build();

        timelineFragmentComponent = DaggerTimelineFragmentComponent
                .builder().networkModule(new NetworkModule()).build();

        instance = getApplicationContext();
    }


}
