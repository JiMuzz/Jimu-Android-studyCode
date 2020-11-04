package com.example.studynote.actvitiytest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class ActivityA : AppCompatActivity() {
    private val TAG = "ActivityA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        Log.e("lz", TAG + "onCreate")

        val uri: Uri = Uri.parse("https://site241962.c.tsichuan.com/venues-list?type=42")
        val value: String? = uri.getQueryParameter("type")
       var set1= uri.queryParameterNames
        Log.e(TAG,"value="+value)

    }

    public fun clickA(view: View) {
        startActivity(Intent(this, ActivityB::class.java))
    }

    override fun onResume() {
        super.onResume()
        Log.e("lz", TAG + "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.e("lz", TAG + "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.e("lz", TAG + "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("lz", TAG + "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("lz", TAG + "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("lz", TAG + "onRestart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("lz", TAG + "onNewIntent")
    }

    class MyCallThread : Callable<String> {
        override fun call(): String {
            return "i got it"
        }

    }

    fun test() {
        var task = FutureTask(MyCallThread())
        var t1 = Thread(task, "test")
        t1.start()
        try {
            //获取结果
            var result = task.get()
        } catch (e: Exception) {
        }
    }


}
