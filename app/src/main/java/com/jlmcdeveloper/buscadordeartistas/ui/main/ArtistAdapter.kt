package com.jlmcdeveloper.buscadordeartistas.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem
import com.jlmcdeveloper.buscadordeartistas.databinding.ArtsItemBinding



class ArtistAdapter(val items: ArrayList<ArtistItem>) :RecyclerView.Adapter<ArtistAdapter.ArtistHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ArtsItemBinding = ArtsItemBinding.inflate(inflater, parent, false)
        return ArtistHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(listNote: ArrayList<ArtistItem>) {
        items.clear()
        items.addAll(listNote.toMutableList())
        notifyDataSetChanged()
    }


    //----------------- ViewHolder ---------------------
    class ArtistHolder(val binding: ArtsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ArtistItem) {
            binding.artist = item
            item.setImage(binding.image)
            binding.executePendingBindings()
        }
    }
}