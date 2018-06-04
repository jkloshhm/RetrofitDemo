package com.example.retrofitrxjavaokhttpdemo;

import android.app.Application;
import android.content.Context;

/**
 * PS: Activity
 *
 * @author jack.guo,  Date on 2018/6/4.
 */
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
