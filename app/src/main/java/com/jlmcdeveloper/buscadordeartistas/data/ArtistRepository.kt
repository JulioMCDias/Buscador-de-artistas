package com.jlmcdeveloper.buscadordeartistas.data
import com.jlmcdeveloper.buscadordeartistas.data.api.ArtistDataSource
import com.jlmcdeveloper.buscadordeartistas.data.model.Artist


class ArtistRepository(private val artistDataSource: ArtistDataSource): ArtistDataSource {


    override fun listArtist(success: (List<Artist>) -> Unit, failure: () -> Unit) {
        artistDataSource.listArtist(success,failure)
    }


}