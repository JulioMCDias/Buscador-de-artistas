package com.jlmcdeveloper.buscadordeartistas.data.api


interface ArtistDataSource {
    fun listArtist(success : (List<ArtistResponse.Artist>) -> Unit, failure: () -> Unit)

}