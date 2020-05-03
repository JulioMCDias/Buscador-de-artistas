package com.jlmcdeveloper.buscadordeartistas.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem

abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
    abstract fun bind(item: ArtistItem?)
}