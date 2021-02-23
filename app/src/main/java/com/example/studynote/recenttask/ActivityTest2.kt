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

        image.setBackgroundColor(resources.getColor(R.color.leak_canary_count_new_border))
        image.setText("ActivityTest2")


    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("lz",TAG+"onNewIntent")
    }
}
