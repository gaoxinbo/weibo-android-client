package gaoxinbo.sinaweiboclient.listener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

import javax.inject.Inject;

import gaoxinbo.sinaweiboclient.activity.MainActivity;
import gaoxinbo.sinaweiboclient.application.WeiboApplication;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboWrapper;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class WBAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener{
    @Inject
    WeiboWrapper weiboWrapper;

    AppCompatActivity appCompatActivity;

    public WBAuthListener(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
        ((WeiboApplication)appCompatActivity.getApplication()).getLoginActivityComponent().inject(this);
    }

    @Override
    public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
        weiboWrapper.setAccessToken(oauth2AccessToken.getToken());

        Intent intent = new Intent(this.appCompatActivity, MainActivity.class);
        intent.putExtra(ACCESS_TOKEN, oauth2AccessToken.getToken());
        appCompatActivity.startActivity(intent);
    }

    @Override
    public void cancel() {

    }

    @Override
    public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        Log.e(this.getClass().getSimpleName(), "error:" + wbConnectErrorMessage.getErrorMessage());
    }
}
