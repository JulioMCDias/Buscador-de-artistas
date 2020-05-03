package com.jlmcdeveloper.buscadordeartistas.data.api

data class ArtistResponse(val art: Art?) {

    data class Art(val day: Rank?, val week: Rank?, val month: Rank?)

    data class Rank(
        val all: List<Artist>?,
        val nacional: List<Artist>?,
        val internacional: List<Artist>?
    )

    data class Artist(val name: String?, val url: String?, val pic_small: String?, val views: Int?)
}