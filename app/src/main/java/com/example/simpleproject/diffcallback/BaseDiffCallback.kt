package com.example.simpleproject.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.simpleproject.data.local.BaseItem

class BaseDiffCallback<T : BaseItem> : DiffUtil.ItemCallback<T>(){
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem

}