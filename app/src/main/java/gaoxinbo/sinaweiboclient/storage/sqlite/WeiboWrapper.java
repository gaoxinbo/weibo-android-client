package gaoxinbo.sinaweiboclient.storage.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;

import java.util.Optional;

import gaoxinbo.sinaweiboclient.application.WeiboApplication;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class WeiboWrapper {
    WeiboDbHelper weiboDbHelper = new WeiboDbHelper(WeiboApplication.getInstance());

    public Optional<String> getAccessToken(){
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
            return Optional.of(token);
        }
        return Optional.empty();
    }

    public void setAccessToken(String accessToken) {
        SQLiteDatabase db = weiboDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeiboContract.WeiboEntry.KEY, ACCESS_TOKEN);
        values.put(WeiboContract.WeiboEntry.VALUE, accessToken);
        db.insert(WeiboContract.WeiboEntry.TABLE_NAME, null, values);
    }

    public void deleteAccessToken() {
        SQLiteDatabase writableDatabase = weiboDbHelper.getWritableDatabase();
        writableDatabase.delete(WeiboContract.WeiboEntry.TABLE_NAME,
                WeiboContract.WeiboEntry.KEY + " =? ",
                new String[]{ACCESS_TOKEN});
    }
}
