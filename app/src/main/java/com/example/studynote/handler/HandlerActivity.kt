package com.example.studynote.handler

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R
import kotlinx.android.synthetic.main.activity_handler2.*
import java.lang.ref.WeakReference
import kotlin.concurrent.thread


class HandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler2)

//        var manager=MemoryManager.getInstance(this)

//        mHandler.sendEmptyMessage(0)

//        MyHandler(WeakReference(this)).sendEmptyMessageDelayed(0, 20000)

        btn2.setOnClickListener {
            finish()
        }

//        MyThread(WeakReference(this)).start()


        thread {
            Thread.sleep(20000)
            btn2.setText("2222")
//            mHandler.sendEmptyMessage(0)
        }

//        object : Thread() {
//            override fun run() {
//                super.run()
//                SystemClock.sleep(20000)
////                btn2.setText("2222")
//            }
//        }.start()

    }

    fun changeBtn() {
        btn2.setText("2222")
    }

    var mHandler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            btn2.setText("2222")
        }
    }


    class MyHandler(var mActivity: WeakReference<HandlerActivity>):Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            mActivity.get()?.changeBtn()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mHandler.removeCallbacksAndMessages(null)
    }


}