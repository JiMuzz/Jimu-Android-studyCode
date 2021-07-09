package com.example.studynote.exception

import android.content.Context
import android.os.Handler
import android.os.Looper


class CrashHandler private constructor(): Thread.UncaughtExceptionHandler {
    private var context: Context? = null
    fun init(context: Context?) {
        this.context = context
        Thread.setDefaultUncaughtExceptionHandler(this)

//        Handler(Looper.getMainLooper()).post {
//            while (true) {
//                try {
//                    //主线程异常拦截
//                    Looper.loop()
//                } catch (e: Throwable) {
//                }
//            }
//        }

    }

    override fun uncaughtException(t: Thread, e: Throwable) {

    }

    companion object {
        val instance: CrashHandler by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CrashHandler() }
    }
}

