package com.example.studynote.appstartup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

// Initializes facebooksdk.
class LibraryJInitializer : Initializer<Unit> {
    private  val TAG = "lz"

    override fun create(context: Context): Unit {
        try {
            Thread.sleep(TIME_COUST)
            Log.e(TAG,"LibraryJInitializer")
        } catch (ex: Exception) {
        }
    }


    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}