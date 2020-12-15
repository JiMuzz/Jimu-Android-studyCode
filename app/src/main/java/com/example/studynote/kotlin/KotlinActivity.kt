package com.example.studynote.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        var test = "nihao";
        var test2 = "heihei"

        getApplication()

        btn.setOnClickListener {
            showToast("11")

        }
    }
}