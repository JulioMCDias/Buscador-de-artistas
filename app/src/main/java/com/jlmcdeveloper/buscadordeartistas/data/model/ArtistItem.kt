package com.jlmcdeveloper.buscadordeartistas.data.model

import android.widget.ImageView
import com.squareup.picasso.Picasso

data class ArtistItem(
    val name: String?,
    val url: String?,
    val pic_small: String?,
    val views: String?,
    val favorit: Boolean?
){

    fun setImage(imageView: ImageView) {
        Picasso.get()
            .load(pic_small)
            .into(imageView)
    }

}