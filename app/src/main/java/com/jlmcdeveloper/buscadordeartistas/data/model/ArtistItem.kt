package com.jlmcdeveloper.buscadordeartistas.data.model

import android.widget.ImageView
import com.jlmcdeveloper.buscadordeartistas.data.api.ArtistResponse
import com.squareup.picasso.Picasso

data class ArtistItem(
    val idArtist: String?,
    val name: String?,
    val url: String?,
    val pic_small: String?,
    val views: String?,
    var favorit: Boolean?
){
    constructor(artist: ArtistResponse.Artist, favor: Boolean?) : this(
        idArtist = artist.id,
        name = artist.name,
        url = artist.url,
        pic_small = artist.pic_small,
        views = artist.views.toString(),
        favorit  = favor
    )

    fun setImage(imageView: ImageView) {
        Picasso.get()
            .load(pic_small)
            .into(imageView)
    }

    fun btnFavorite(){
        favorit = true xor favorit!!
    }

}