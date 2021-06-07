package com.example.simpleproject

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(convertView : View) : RecyclerView.ViewHolder(convertView){
    open fun bind(item : T) {

    }
}