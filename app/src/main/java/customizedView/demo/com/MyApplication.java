package customizedView.demo.com;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import customizedView.demo.com.utils.Constants;

public class MyApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Constants.TAG, "onCreate: MyApplication " );
        mContext = getApplicationContext();
    }
}
