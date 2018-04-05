package gaoxinbo.sinaweiboclient.listener;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

import gaoxinbo.sinaweiboclient.activity.TimelineActivity;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class WBAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener{
    AppCompatActivity appCompatActivity;
    public WBAuthListener(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
        SharedPreferences sharedPref = appCompatActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(ACCESS_TOKEN, oauth2AccessToken.getToken());
        editor.commit();

        Intent intent = new Intent(this.appCompatActivity, TimelineActivity.class);
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
