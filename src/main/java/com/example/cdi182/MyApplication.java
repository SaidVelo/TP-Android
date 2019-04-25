package com.example.cdi182;

import android.app.Application;

import com.squareup.otto.Bus;

public class MyApplication extends Application {

    private static Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();

        bus = new Bus();
    }

    public static Bus getBus() {
        return bus;
    }
}
