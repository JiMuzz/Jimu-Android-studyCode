package com.example.studynote.blog.jetpack

import android.content.Context
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*


class BindingActivity : AppCompatActivity(), LifecycleOwner {


    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)

    }

    public override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }


    internal class MyLocationListener(
            private val context: Context,
            private val callback: (Location) -> Unit
    ) : LifecycleObserver {

//        myLocationListener = MyLocationListener(this) { location ->
//            // update UI
//        }
//        lifecycle.addObserver(myLocationListener)

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun start() {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun stop() {
            // disconnect if connected
        }
    }

}