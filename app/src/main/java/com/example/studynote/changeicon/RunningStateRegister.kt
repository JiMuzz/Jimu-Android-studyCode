package com.example.studynote.changeicon

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * 应用运行状态注册器
 */
object RunningStateRegister {

    fun register(application: Application, callback: StateCallback) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            private var startedActivityCount = 0


            override fun onActivityStarted(activity: Activity) {
                if (startedActivityCount == 0) {
                    callback.onForeground()
                }
                startedActivityCount++
            }

            override fun onActivityStopped(activity: Activity) {
                startedActivityCount--
                if (startedActivityCount == 0) {
                    callback.onBackground()
                }
            }


            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityResumed(activity: Activity) {
            }
        })
    }

    interface StateCallback{
       fun onForeground()
       fun onBackground()
    }

}