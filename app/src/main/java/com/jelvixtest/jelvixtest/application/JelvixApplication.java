package com.jelvixtest.jelvixtest.application;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.jelvixtest.jelvixtest.di.AuthComponent;
import com.jelvixtest.jelvixtest.di.DaggerAuthComponent;
import com.jelvixtest.jelvixtest.di.modules.ContextModule;

public class JelvixApplication extends Application {
    public static AuthComponent sAuthComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        sAuthComponent = DaggerAuthComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static AuthComponent getAuthComponent() {
        return sAuthComponent;
    }
}
