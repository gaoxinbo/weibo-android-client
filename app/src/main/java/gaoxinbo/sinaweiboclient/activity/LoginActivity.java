package gaoxinbo.sinaweiboclient.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import gaoxinbo.sinaweiboclient.Constants;
import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.listener.WBAuthListener;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboContract;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboDbHelper;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class LoginActivity extends AppCompatActivity {
    AuthInfo authInfo;
    SsoHandler ssoHandler;
    WeiboDbHelper weiboDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        init();

    }

    void init() {
        weiboDbHelper = new WeiboDbHelper(this);
        SQLiteDatabase readableDatabase = weiboDbHelper.getReadableDatabase();
        String[] columns = {WeiboContract.WeiboEntry.VALUE};
        Cursor cursor = readableDatabase.query(
                WeiboContract.WeiboEntry.TABLE_NAME,
                columns,
                WeiboContract.WeiboEntry.KEY + " = ?",
                new String[]{ACCESS_TOKEN},
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            String token = cursor.getString(cursor.getColumnIndex(WeiboContract.WeiboEntry.VALUE));
            cursor.close();
            Intent intent = new Intent(this, MainActivity.class);
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
