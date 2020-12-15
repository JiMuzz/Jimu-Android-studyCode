package com.example.studynote.kotlin

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.showToast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun Activity.getApplication(){
    Toast.makeText(this,"heihei",Toast.LENGTH_SHORT).show()
}