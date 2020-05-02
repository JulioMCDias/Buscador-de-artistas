package com.jlmcdeveloper.buscadordeartistas.data.api


interface ArtistDataSource {
    fun listArtist(success : (List<Artist>) -> Unit, failure: () -> Unit)

}