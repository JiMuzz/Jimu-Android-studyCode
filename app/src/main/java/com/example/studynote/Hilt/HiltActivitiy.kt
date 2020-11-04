package com.example.studynote.Hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivitiy : AppCompatActivity() {

    @Inject
    lateinit var user: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showToast(user.name)
    }
}
