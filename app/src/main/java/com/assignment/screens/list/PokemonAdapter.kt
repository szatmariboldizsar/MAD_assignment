package com.assignment.screens.list;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.database.Pokemon
import com.assignment.databinding.PokemonListItemBinding

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(PokemonDiffCallback()) {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val item = getItem(position)
                holder.bind(item)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder.from(parent)
        }

        class ViewHolder private constructor(val binding: PokemonListItemBinding) : RecyclerView.ViewHolder(binding.root){

                fun bind(item: Pokemon) {
                        binding.pokemon = item
                        binding.executePendingBindings()
                }

                companion object {
                        fun from(parent: ViewGroup): ViewHolder {
                                val layoutInflater = LayoutInflater.from(parent.context)
                                val binding = PokemonListItemBinding.inflate(layoutInflater, parent, false)
                                return ViewHolder(binding)
                        }
                }
        }
}


class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {

        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.Id == newItem.Id
        }


        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
        }


}