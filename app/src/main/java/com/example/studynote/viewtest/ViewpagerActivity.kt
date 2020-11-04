package com.example.studynote.viewtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R
import kotlinx.android.synthetic.main.activity_viewpager.*

class ViewpagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        var list = arrayListOf<String>("#fa9f05", "#00f900", "#942192", "#ff2600", "#0433ff", "#000000")

        viewpager.apply {
            offscreenPageLimit = 5
            setPageTransformer(true, GalleryTransformer(5, 200f))
            adapter = PagerAdapter(this@ViewpagerActivity, list as ArrayList<String>)
        }
    }
}