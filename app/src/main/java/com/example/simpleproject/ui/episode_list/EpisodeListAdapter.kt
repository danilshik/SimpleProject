package com.example.simpleproject.ui.episode_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simpleproject.BaseViewHolder
import com.example.simpleproject.data.local.EpisodeItem
import com.example.simpleproject.data.local.LocationItem
import com.example.simpleproject.databinding.ItemEpisodeListBinding
import com.example.simpleproject.databinding.ItemLocationListBinding
import com.example.simpleproject.diffcallback.BaseDiffCallback

class EpisodeListAdapter(private val listener: EpisodeListListener) : ListAdapter<EpisodeItem, BaseViewHolder<EpisodeItem>>(
    BaseDiffCallback<EpisodeItem>()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<EpisodeItem> {
        return ViewHolder(
            ItemEpisodeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: BaseViewHolder<EpisodeItem>, position: Int) {
        holder.bind(getItem(position))
    }


    fun addItems(newItems : List<EpisodeItem>){
        submitList(currentList.plus(newItems))
    }

    fun clearItems(){
        submitList(listOf())
    }


    inner class ViewHolder(private val bind : ItemEpisodeListBinding) : BaseViewHolder<EpisodeItem>(bind.root){

        override fun bind(item: EpisodeItem) {
            with(bind.tvEpisode){
                text = item.episode
            }
            with(bind.tvName){
                text = item.name
            }
            with(bind.tvAirDate){
                text = item.airDate
            }
            itemView.setOnClickListener { listener.onClick(item) }
        }
    }




}

interface EpisodeListListener{
    fun onClick(item : EpisodeItem)
}
