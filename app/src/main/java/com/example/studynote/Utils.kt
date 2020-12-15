package com.example.studynote

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.showToast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}

