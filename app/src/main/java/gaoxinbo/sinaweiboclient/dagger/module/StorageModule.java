package gaoxinbo.sinaweiboclient.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gaoxinbo.sinaweiboclient.application.WeiboApplication;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboDbHelper;

@Module
public class StorageModule {

    @Singleton
    @Provides
    public WeiboDbHelper providesWeiboDbHelper() {
        return new WeiboDbHelper(WeiboApplication.getInstance());
    }


}
