package com.example.rickandmorty

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.RecyclerItemBinding
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.ui.activity.DescriptionItemActivity
import com.squareup.picasso.Picasso

class CharacterAdapter() : ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(Comparator()) {
    lateinit var onItemClick: ((Character) -> Unit)
    class CharacterViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecyclerItemBinding.bind(item)
        fun bind(character: Character) = with(binding){
            Picasso.get().load(character.image).into(avatar)
            name.text = character.name
            status.text = character.status
            bookmark.setOnClickListener {
                bookmark.setImageResource(R.drawable.bookmark_check)
            }
        }
    }
    class Comparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }
    override fun getItemCount(): Int {
        return currentList.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context.applicationContext)
            .inflate(R.layout.recycler_item, parent, false)
        return CharacterViewHolder(view)
    }
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemClick.invoke(getItem(position))
        }
    }
}