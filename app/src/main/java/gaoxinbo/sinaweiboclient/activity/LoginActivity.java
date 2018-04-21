package gaoxinbo.sinaweiboclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.util.Optional;

import javax.inject.Inject;

import gaoxinbo.sinaweiboclient.Constants;
import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.application.WeiboApplication;
import gaoxinbo.sinaweiboclient.listener.WBAuthListener;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboWrapper;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class LoginActivity extends AppCompatActivity {
    AuthInfo authInfo;
    SsoHandler ssoHandler;

    @Inject
    WeiboWrapper weiboWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ((WeiboApplication)getApplication()).getLoginActivityComponent().inject(this);
        init();

    }

    void init() {
        Optional<String> accessToken = weiboWrapper.getAccessToken();
        if (accessToken.isPresent()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(ACCESS_TOKEN, accessToken.get());
            startActivity(intent);
        }


        authInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        WbSdk.install(this, authInfo);

        ssoHandler = new SsoHandler(LoginActivity.this);

        findViewById(R.id.login_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ssoHandler.authorize(new WBAuthListener(LoginActivity.this));
                    }
                }
        );

    }

}
