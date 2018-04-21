package gaoxinbo.sinaweiboclient.application;

import android.app.Application;
import android.content.Context;

public class WeiboApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getInstance() {
        return mContext;
    }


}
