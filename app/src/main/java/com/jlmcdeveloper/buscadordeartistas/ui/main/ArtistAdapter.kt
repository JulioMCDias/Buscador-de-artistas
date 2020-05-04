package com.jlmcdeveloper.buscadordeartistas.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem
import com.jlmcdeveloper.buscadordeartistas.databinding.ArtsItemBinding
import com.jlmcdeveloper.buscadordeartistas.databinding.EmptyItemBinding
import com.jlmcdeveloper.buscadordeartistas.utils.ValueFilter


class ArtistAdapter(
    private val listData: ArrayList<ArtistItem>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val listItem = ArrayList<ArtistItem>()
    private val VIEW_TYPE_EMPTY = 0
    private val VIEW_TYPE_NORMAL = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        if(viewType == VIEW_TYPE_NORMAL)
            return ArtistHolder(viewModel, context,
                ArtsItemBinding.inflate(inflater, parent, false))

        return EmptyViewHolder(viewModel, EmptyItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return when {
            viewModel.loadingVisibility.value!! -> listItem.size
            listData.isEmpty() -> 1
            else -> listItem.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            viewModel.loadingVisibility.value!! -> VIEW_TYPE_NORMAL
            listData.isEmpty() -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_NORMAL
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when {
            viewModel.loadingVisibility.value!! -> holder.bind(listItem[position])
            listData.isEmpty() -> holder.bind(null)
            else -> holder.bind(listItem[position])
        }
    }

    //-------------- atualização da lista ---------------
    fun updateItems(listNote: ArrayList<ArtistItem>) {
        listData.clear()
        listData.addAll(listNote.toMutableList())
        filter.filter("")
    }


    // --------------- filtro da pesquisa ----------------
    val filter = ValueFilter(listData, condition = { item -> item.name!! }, result = { list ->
        listItem.clear()
        listItem.addAll(list)
        notifyDataSetChanged()
    })




    //====================== ViewHolder =============================
    class ArtistHolder(
        private val viewModel: MainViewModel,
        private val context: Context,
        private val binding: ArtsItemBinding) : BaseViewHolder(binding.root) {

        override fun bind(item: ArtistItem?) {
            binding.artist = item
            item!!.setImage(binding.image)
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

    //====================== ViewHolder =============================
    class EmptyViewHolder(private val viewModel: MainViewModel, private val binding: EmptyItemBinding) :
        BaseViewHolder(binding.root) {

        override fun bind(item: ArtistItem?) {
            binding.btnUpdate.setOnClickListener {
                viewModel.loadListArtist()
            }
        }

    }
}