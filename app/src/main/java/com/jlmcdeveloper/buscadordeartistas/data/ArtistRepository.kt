package com.jlmcdeveloper.buscadordeartistas.data
import com.jlmcdeveloper.buscadordeartistas.data.api.Artist
import com.jlmcdeveloper.buscadordeartistas.data.api.ArtistDataSource


class ArtistRepository(private val artistDataSource: ArtistDataSource): ArtistDataSource {


    override fun listArtist(success: (List<Artist>) -> Unit, failure: () -> Unit) {
        artistDataSource.listArtist(success,failure)
    }


}