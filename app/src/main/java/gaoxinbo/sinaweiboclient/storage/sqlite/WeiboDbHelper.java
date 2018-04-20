package gaoxinbo.sinaweiboclient.storage.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeiboDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WeiboContract.WeiboEntry.TABLE_NAME + " (" +
                    WeiboContract.WeiboEntry._ID + " INTEGER PRIMARY KEY," +
                    WeiboContract.WeiboEntry.KEY + " TEXT," +
                    WeiboContract.WeiboEntry.VALUE + " TEXT)";

    private static final String DATABASE_NAME = "weibo.db";
    private static final Integer DATABASE_VERSION = 1;

    public WeiboDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
