/***************************************************************************
 * Copyright (c) YSJC Info Tech Co. Ltd
 *
 * @version: 1.0
 ***************************************************************************/
package com.example.studynote;


import android.app.Application;

import com.example.studynote.changeicon.LauncherIconManager;
import com.example.studynote.changeicon.SplashActivity;
import com.example.studynote.changeicon.SwitchIconTask;
import com.example.studynote.exception.CrashHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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


        //换图标
//        LauncherIconManager.INSTANCE.register(this);
//        SimpleDateFormat  format =new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            LauncherIconManager.INSTANCE.addNewTask(
//                    new SwitchIconTask(
//                            SplashActivity.class.getName(),
//                    "com.example.studynote.SplashAliasActivity",
//                            format.parse("2020-11-07").getTime(),
//                    format.parse("2020-11-08").getTime()),
//                    new SwitchIconTask(
//                    SplashActivity.class.getName(),
//                    "com.example.studynote.SplashAlias2Activity",
//                    format.parse("2020-11-10").getTime(),
//                    format.parse("2020-11-11").getTime()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }

    public static MainApplication getInstance() {
        return sInstance;
    }

}
