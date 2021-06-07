package com.example.simpleproject.ui.location_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleproject.BaseViewHolder
import com.example.simpleproject.data.local.LocationItem
import com.example.simpleproject.databinding.ItemLocationListBinding
import com.example.simpleproject.diffcallback.BaseDiffCallback

class LocationListAdapter(private val listener: LocationListListener) : ListAdapter<LocationItem, BaseViewHolder<LocationItem>>(BaseDiffCallback<LocationItem>()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<LocationItem> {
        return ViewHolder(
            ItemLocationListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: BaseViewHolder<LocationItem>, position: Int) {
        holder.bind(getItem(position))
    }

    //Может дублировать элементы
    fun addItems(newItems : List<LocationItem>){
        submitList(currentList.plus(newItems))
    }

    fun clearItems(){
        submitList(listOf())
    }


    inner class ViewHolder(private val bind : ItemLocationListBinding) : BaseViewHolder<LocationItem>(bind.root){

        override fun bind(item: LocationItem) {
            with(bind.tvDimension){
                text = item.dimension
            }
            with(bind.tvName){
                text = item.name
            }
            with(bind.tvType){
                text = item.type
            }
            itemView.setOnClickListener { listener.onClick(item) }
        }
    }




}

interface LocationListListener{
    fun onClick(item : LocationItem)
}
