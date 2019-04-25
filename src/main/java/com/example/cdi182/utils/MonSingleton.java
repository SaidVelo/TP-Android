package com.example.cdi182.utils;

public class MonSingleton {

    private static MonSingleton instance;

    private MonSingleton() {

    }

    public synchronized static MonSingleton getInstance() {
        if (instance == null) {
            instance = new MonSingleton();
        }
        return instance;
    }
}
