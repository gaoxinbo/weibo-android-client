package gaoxinbo.sinaweiboclient.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import gaoxinbo.sinaweiboclient.application.WeiboApplication;

@Module
public class ApplicationiModule {

    @Provides
    public Context providesContenxt() {
        return WeiboApplication.getInstance();
    }
}
