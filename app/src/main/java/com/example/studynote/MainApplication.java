/***************************************************************************
 * Copyright (c) YSJC Info Tech Co. Ltd
 *
 * @version: 1.0
 ***************************************************************************/
package com.example.studynote;


import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * AppContext
 */
@HiltAndroidApp
public class MainApplication extends Application {
    private static MainApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

    }

    public static MainApplication getInstance() {
        return sInstance;
    }

}
