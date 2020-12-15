package com.example.studynote.exception

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R


class ExceptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exception)

//        throw RuntimeException("主线程异常")


//        btn.setOnClickListener {
//            throw RuntimeException("主线程异常")
//        }
//        btn2.setOnClickListener {
//            thread {
//                throw RuntimeException("子线程异常")
//            }
//        }
//
//        btn3.setOnClickListener {
//            showToast("程序还是正常的")
//        }
    }
}