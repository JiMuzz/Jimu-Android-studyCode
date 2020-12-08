package com.example.studynote.recycleview

import com.example.studynote.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(dataset: Array<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private val dataset: Array<String>
    private var counterOnCreateViewHolder = 0
    private var counterOnBindViewHolder = 0

    class ViewHolder(var textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.e(LOG_TAG, "onCreateViewHolder")
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_textview, parent, false)
        return ViewHolder(view as TextView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e(LOG_TAG, "onBindViewHolder ($position)")
        holder.textView.text = dataset[position]
    }

    override fun getItemCount(): Int {
        // Log.d(LOG_TAG, "getItemCount");
        return dataset.size
    }

    companion object {
        private const val LOG_TAG = "RecyclerViewAdapter"
    }

    init {
        Log.d(LOG_TAG, "Construct")
        this.dataset = dataset
    }
}