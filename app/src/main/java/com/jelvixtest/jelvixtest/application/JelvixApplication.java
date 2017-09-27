package com.jelvixtest.jelvixtest.application;

import android.app.Application;
import android.support.multidex.MultiDex;

public class JelvixApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
