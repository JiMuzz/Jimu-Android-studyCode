package com.example.studynote.threadUI

import android.app.Activity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R
import com.example.studynote.showToast
import kotlinx.android.synthetic.main.activity_ui.*
import kotlinx.android.synthetic.main.activity_ui.view.*
import kotlin.concurrent.thread

class UIMainActivity : AppCompatActivity() {

    lateinit var mToast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)

//        thread {
//            Looper.prepare()
//            mToast=Toast.makeText(this@UIMainActivity,"我去年买了个表",Toast.LENGTH_LONG)
//            mToast.show()
//            Looper.loop()
//        }

//        thread {
//            Thread.sleep(3000)
//            btn_ui.text="年轻人要讲武德"
//        }


        thread {
            Thread.sleep(3000)
            progress.max=180
        }

        btn_ui.setOnClickListener {
            mToast.setText("我今年买了个表")
            mToast.show()
        }
    }

}