package gaoxinbo.sinaweiboclient.listener;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

import gaoxinbo.sinaweiboclient.activity.MainActivity;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboContract;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboDbHelper;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class WBAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener{
    WeiboDbHelper weiboDbHelper;

    AppCompatActivity appCompatActivity;
    public WBAuthListener(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
        weiboDbHelper = new WeiboDbHelper(appCompatActivity);
    }

    @Override
    public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
        SQLiteDatabase db = weiboDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeiboContract.WeiboEntry.KEY, ACCESS_TOKEN);
        values.put(WeiboContract.WeiboEntry.VALUE, oauth2AccessToken.getToken());
        db.insert(WeiboContract.WeiboEntry.TABLE_NAME, null, values);


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
