package gaoxinbo.sinaweiboclient.application;

import android.app.Application;
import android.content.Context;

import gaoxinbo.sinaweiboclient.dagger.component.DaggerLoginActivityComponent;
import gaoxinbo.sinaweiboclient.dagger.component.DaggerSettingFragmentComponent;
import gaoxinbo.sinaweiboclient.dagger.component.LoginActivityComponent;
import gaoxinbo.sinaweiboclient.dagger.component.SettingFragmentComponent;
import gaoxinbo.sinaweiboclient.dagger.module.StorageModule;

public class WeiboApplication extends Application {
    private static Context mContext;

    LoginActivityComponent loginActivityComponent;
    SettingFragmentComponent settingFragmentComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        loginActivityComponent = DaggerLoginActivityComponent
                .builder().storageModule(new StorageModule()).build();

        settingFragmentComponent = DaggerSettingFragmentComponent
                .builder().storageModule(new StorageModule()).build();

        mContext = getApplicationContext();
    }

    public static Context getInstance() {
        return mContext;
    }

    public LoginActivityComponent getLoginActivityComponent() {
        return this.loginActivityComponent;
    }

    public SettingFragmentComponent getSettingFragmentComponent() {
        return this.settingFragmentComponent;
    }
}
