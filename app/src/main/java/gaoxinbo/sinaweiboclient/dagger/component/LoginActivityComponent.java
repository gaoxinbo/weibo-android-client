package gaoxinbo.sinaweiboclient.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import gaoxinbo.sinaweiboclient.activity.LoginActivity;
import gaoxinbo.sinaweiboclient.dagger.module.StorageModule;
import gaoxinbo.sinaweiboclient.listener.WBAuthListener;

@Singleton
@Component(modules = {StorageModule.class})
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);

    void inject(WBAuthListener wbAuthListener);
}
