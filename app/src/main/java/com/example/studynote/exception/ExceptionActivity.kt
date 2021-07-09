package com.example.studynote.exception

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R
import com.example.studynote.showToast
import kotlinx.android.synthetic.main.activity_exception.*
import kotlin.concurrent.thread


class ExceptionActivity : AppCompatActivity() {
    val name:String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exception)

//        throw RuntimeException("主线程异常")


        btn.setOnClickListener {
            showToast("1111")
            Log.e("lz3","111"+Thread.currentThread().name)
            throw RuntimeException("主线程异常")
        }
        btn2.setOnClickListener {
            Thread {
                Log.e("lz3","222"+Thread.currentThread().name)
                throw NumberFormatException("子线程异常")
            }.start()
//            thread {
//
////                name!!.length
////                Log.e("lz3","222"+Thread.currentThread().name)
////                throw NumberFormatException("子线程异常")
//            }.start()
        }


//
//        btn3.setOnClickListener {
//            showToast("程序还是正常的")
//        }
    }
}