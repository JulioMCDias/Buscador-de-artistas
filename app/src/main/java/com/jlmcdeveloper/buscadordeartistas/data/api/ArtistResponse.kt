package com.jlmcdeveloper.buscadordeartistas.data.api

import com.jlmcdeveloper.buscadordeartistas.data.model.Artist

data class ArtistResponse(val art: Art?)

data class Art(val day: Rank?, val week: Rank?, val month: Rank?)

data class Rank(val all: List<Artist>?, val nacional: List<Artist>?, val internacional: List<Artist>?)

