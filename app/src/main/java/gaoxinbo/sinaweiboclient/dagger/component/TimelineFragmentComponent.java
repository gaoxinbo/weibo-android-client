package gaoxinbo.sinaweiboclient.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import gaoxinbo.sinaweiboclient.dagger.module.AdapterModule;
import gaoxinbo.sinaweiboclient.dagger.module.ApplicationiModule;
import gaoxinbo.sinaweiboclient.dagger.module.BasicModule;
import gaoxinbo.sinaweiboclient.dagger.module.NetworkModule;
import gaoxinbo.sinaweiboclient.dagger.module.StorageModule;
import gaoxinbo.sinaweiboclient.fragment.TimelineFragment;

@Singleton
@Component(modules = {NetworkModule.class, StorageModule.class, BasicModule.class, AdapterModule.class, ApplicationiModule.class})
public interface TimelineFragmentComponent {
    void inject(TimelineFragment timelineFragment);
}
