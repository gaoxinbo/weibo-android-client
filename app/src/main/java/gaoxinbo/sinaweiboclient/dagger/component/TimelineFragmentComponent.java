package gaoxinbo.sinaweiboclient.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import gaoxinbo.sinaweiboclient.dagger.module.NetworkModule;
import gaoxinbo.sinaweiboclient.fragment.TimelineFragment;

@Singleton
@Component(modules = {NetworkModule.class})
public interface TimelineFragmentComponent {
    void inject(TimelineFragment timelineFragment);
}
