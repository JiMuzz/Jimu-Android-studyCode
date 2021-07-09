package com.example.studynote.recenttask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R
import kotlinx.android.synthetic.main.activity_recent.*

class ActivityTest2 :AppCompatActivity(){
    private val TAG = "ActivityRecent"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("lz",TAG+"onCreate")
        setContentView(R.layout.activity_recent)

        image.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        image.setText("ActivityTest2")
        image.setOnClickListener {
            var intent = Intent(this, ActivityTest1::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
//            var intent = Intent("com.jimu.test2")
            startActivity(intent)
        }

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("lz",TAG+"onNewIntent")
    }
}
