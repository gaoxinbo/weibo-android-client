package gaoxinbo.sinaweiboclient.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import gaoxinbo.sinaweiboclient.Constants;
import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.listener.WBAuthListener;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class LoginActivity extends AppCompatActivity {
    AuthInfo authInfo;
    SsoHandler ssoHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    void init() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String token = sharedPref.getString(ACCESS_TOKEN, null);
        if (token != null) {
            Intent intent = new Intent(this, TimelineActivity.class);
            intent.putExtra(ACCESS_TOKEN, token);
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
