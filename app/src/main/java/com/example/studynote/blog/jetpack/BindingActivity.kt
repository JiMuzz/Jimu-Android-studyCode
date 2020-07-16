package com.example.studynote.blog.jetpack

import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


class BindingActivity : Activity() {

    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myLocationListener = MyLocationListener(this) { location ->
            // update UI
        }

    }

    public override fun onStart() {
        super.onStart()
        myLocationListener.start()
    }

    public override fun onStop() {
        super.onStop()
        myLocationListener.stop()
    }

    internal class MyLocationListener(
            private val context: Context,
            private val callback: (Location) -> Unit
    ) {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun start() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun stop() {
            // disconnect if connected
        }
    }

}