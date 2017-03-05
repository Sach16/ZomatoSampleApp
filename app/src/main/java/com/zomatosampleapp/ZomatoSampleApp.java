package com.zomatosampleapp;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by Ramesh on 3/7/16.
 */
public class ZomatoSampleApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
