package com.example.studynote

import android.app.Application
import com.example.studynote.exception.CrashHandler

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        CrashHandler.instance.init(this)
    }
}