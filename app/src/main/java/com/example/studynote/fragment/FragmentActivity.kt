package com.example.studynote.fragment

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import com.example.studynote.R
import java.util.concurrent.Executors

public class FragmentActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fragment = MyFragment()!!
        var transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragment1, fragment, "test").commitAllowingStateLoss()
        transaction.show(fragment)

//        val singleThread = Executors.newSingleThreadExecutor()
//        btn.onClick {
//            singleThread.submit { throw RuntimeException("") }
//        }


//        val editText = EditText(this)
//        editText.setText("12")
//
//        editText.text="123"
    }
}