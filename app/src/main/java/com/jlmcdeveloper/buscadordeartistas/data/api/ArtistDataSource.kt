package com.jlmcdeveloper.buscadordeartistas.data.api

import com.jlmcdeveloper.buscadordeartistas.data.model.Artist

interface ArtistDataSource {
    fun listArtist(success : (List<Artist>) -> Unit, failure: () -> Unit)

}