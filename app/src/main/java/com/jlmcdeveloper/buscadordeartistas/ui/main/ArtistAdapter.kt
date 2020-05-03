package com.jlmcdeveloper.buscadordeartistas.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem
import com.jlmcdeveloper.buscadordeartistas.databinding.ArtsItemBinding


class ArtistAdapter(
    private val items: ArrayList<ArtistItem>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<ArtistAdapter.ArtistHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding: ArtsItemBinding = ArtsItemBinding.inflate(inflater, parent, false)
        return ArtistHolder(viewModel, context, binding)
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
    class ArtistHolder(private val viewModel: MainViewModel, private val context: Context, private val binding: ArtsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArtistItem) {
            binding.artist = item
            item.setImage(binding.image)
            binding.executePendingBindings()

            // ----- checkBox favoritos ------
            binding.favourite.setOnClickListener {
                item.btnFavorite()
                viewModel.updateArtistFavorite(item)
            }

            // ----- abrir na web -----
            binding.cadView.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
            }
        }
    }
}