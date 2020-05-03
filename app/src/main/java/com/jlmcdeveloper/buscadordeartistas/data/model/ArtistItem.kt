package com.jlmcdeveloper.buscadordeartistas.data.model

import android.widget.ImageView
import com.jlmcdeveloper.buscadordeartistas.data.api.ArtistResponse
import com.jlmcdeveloper.buscadordeartistas.data.database.model.Favorite
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

    constructor(favorite: Favorite) : this(
        idArtist = favorite.idArtist,
        name = favorite.name,
        url = favorite.url,
        pic_small = favorite.picSmall,
        views = favorite.views,
        favorit  = favorite.enable
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