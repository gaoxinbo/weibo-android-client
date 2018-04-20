package gaoxinbo.sinaweiboclient.storage.sqlite;

import android.provider.BaseColumns;

public final class WeiboContract {
    private WeiboContract() {}

    public static class WeiboEntry implements BaseColumns {
        public static final String TABLE_NAME = "weibo";
        public static final String KEY = "key";
        public static final String VALUE = "value";
    }


}
