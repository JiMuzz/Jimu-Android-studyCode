package com.example.studynote.xiecheng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class XiechengActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        GlobalScope.launch (Dispatchers.Main) {
            ioTask1()
            ioTask1()
            ioTask1()
            updateUI1()
            updateUI2()
            updateUI3()
        }
    }




    suspend fun ioTask1(){
        withContext(Dispatchers.IO){}
    }
    suspend fun ioTask2(){
        withContext(Dispatchers.IO){}
    }
    suspend fun ioTask3(){
        withContext(Dispatchers.IO){}
    }

    fun updateUI1(){
    }
    fun updateUI2(){
    }
    fun updateUI3(){
    }
}