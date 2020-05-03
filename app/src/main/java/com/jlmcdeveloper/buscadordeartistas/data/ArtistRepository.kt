package com.jlmcdeveloper.buscadordeartistas.data

import com.jlmcdeveloper.buscadordeartistas.data.api.ArtistDataSource
import com.jlmcdeveloper.buscadordeartistas.data.database.model.Favorite

interface ArtistRepository : ArtistDataSource {
    fun updateFavorites(favorite: Favorite)
    fun getListFavorites(success : (List<Favorite>) -> Unit, failure: () -> Unit)

    fun logout()
}