package com.example.studynote.recycleview


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studynote.R


class RecycleMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycleview)
        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val myAdapter = MyAdapter(DATASET)
        recyclerView.adapter = myAdapter
    }

    companion object {
        private val DATASET = arrayOf(
                "item0", "item1","item2","item3","item4","item5","item6","item7","item8",
                "item9","item10","item11","item12","item13","item14","item15","item16","item17","item18","item19")
    }
}