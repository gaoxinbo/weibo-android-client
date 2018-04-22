package gaoxinbo.sinaweiboclient.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BasicModule {

    @Singleton
    @Provides
    public Gson providesGson() {
        return new GsonBuilder().setLenient().create();
    }
}
