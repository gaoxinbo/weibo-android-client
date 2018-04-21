package gaoxinbo.sinaweiboclient.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import gaoxinbo.sinaweiboclient.dagger.module.StorageModule;
import gaoxinbo.sinaweiboclient.fragment.SettingsFragment;

@Singleton
@Component(modules = {StorageModule.class})
public interface SettingFragmentComponent {
    void inject(SettingsFragment settingsFragment);
}
