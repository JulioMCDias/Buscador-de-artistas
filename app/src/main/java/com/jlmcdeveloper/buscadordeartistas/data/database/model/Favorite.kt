package com.jlmcdeveloper.buscadordeartistas.data.database.model

import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem

data class Favorite(
    val idArtist: String,
    val name: String,
    val url: String,
    val picSmall: String,
    val views: String,
    var enable: Boolean)
{
    constructor(artistItem: ArtistItem) : this(
        idArtist = artistItem.idArtist!!,
        name = artistItem.name!!,
        url = artistItem.url!!,
        picSmall = artistItem.pic_small!!,
        views = artistItem.views!!,
        enable = artistItem.favorit!!
    )


    companion object{
        const val Table = "favorites"
        const val ColumnName = "name"
        const val ColumnUrl = "url"
        const val ColumnPicSmall = "pic_small"
        const val ColumnViews = "views"
        const val ColumnEnable = "enable"
    }


    fun getRegister(): Map<String, Any> {
        return hashMapOf(
            ColumnName to name,
            ColumnUrl to url,
            ColumnPicSmall to picSmall,
            ColumnViews to views,
            ColumnEnable to enable
        )
    }
}
