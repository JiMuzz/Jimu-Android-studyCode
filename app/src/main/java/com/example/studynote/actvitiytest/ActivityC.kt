package com.example.studynote.actvitiytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R

class ActivityC :AppCompatActivity(){
    private val TAG = "ActivityC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        Log.e("lz",TAG+"onCreate")
    }

    public fun clickA(view: View) {
        startActivity(Intent(this,ActivityB::class.java))
    }

    override fun onResume() {
        super.onResume()
        Log.e("lz",TAG+"onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.e("lz",TAG+"onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.e("lz",TAG+"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("lz",TAG+"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("lz",TAG+"onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("lz",TAG+"onRestart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("lz",TAG+"onNewIntent")
    }
}
