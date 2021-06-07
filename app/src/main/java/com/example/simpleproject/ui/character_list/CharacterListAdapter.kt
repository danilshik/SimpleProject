package com.example.simpleproject.ui.character_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.simpleproject.BaseViewHolder
import com.example.simpleproject.R
import com.example.simpleproject.data.local.CharacterListItem
import com.example.simpleproject.databinding.ItemCharacterListBinding
import com.example.simpleproject.diffcallback.BaseDiffCallback

class CharacterListAdapter(private val listener: CharacterListListener)
    : ListAdapter<CharacterListItem, BaseViewHolder<CharacterListItem>>(
    BaseDiffCallback<CharacterListItem>()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CharacterListItem> {
        return ViewHolder(
            ItemCharacterListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: BaseViewHolder<CharacterListItem>, position: Int) {
        holder.bind(getItem(position))
    }


    fun addItems(newItems : List<CharacterListItem>){
        submitList(currentList.plus(newItems))
    }

    fun clearItems(){
        submitList(listOf())
    }


    inner class ViewHolder(private val bind : ItemCharacterListBinding) : BaseViewHolder<CharacterListItem>(bind.root){

        override fun bind(item: CharacterListItem) {
            with(bind.tvSpecies){
                text = item.species
            }
            with(bind.tvName){
                text = item.name
            }
            with(bind.tvStatus){
                text = item.status
            }
            with(bind.tvGender){
                text = item.gender
            }

            //Стоит ли выносить в Module Glide
            with(bind.ivAvatar){
                Glide.with(this)
                    .load(item.image)
                    .placeholder(R.drawable.ic_place_holder_24)
                    .into(this)
            }

            itemView.setOnClickListener { listener.onClick(item) }
        }
    }




}

interface CharacterListListener{
    fun onClick(item : CharacterListItem)
}
