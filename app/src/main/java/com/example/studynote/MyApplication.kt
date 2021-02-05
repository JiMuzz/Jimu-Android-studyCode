package com.example.studynote

import android.app.Application
import android.graphics.Color
import com.example.studynote.exception.CrashHandler
import leakcanary.LeakCanary

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        CrashHandler.instance.init(this)
    }
}