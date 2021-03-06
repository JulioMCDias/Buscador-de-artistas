package com.jlmcdeveloper.buscadordeartistas.data.api


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppArtistDataSource(private val apiRest: ApiRestServer) : ArtistDataSource {

    override fun listArtist(success: (List<ArtistResponse.Artist>) -> Unit, failure: () -> Unit) {
        val call = apiRest.listArtist(
            ApiEndPoint.type,
            ApiEndPoint.period,
            ApiEndPoint.scope,
            ApiEndPoint.limit,
            ApiEndPoint.apikey)

        call.enqueue(object : Callback<ArtistResponse> {
            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) =
                if (response.isSuccessful) {
                    val artists = mutableListOf<ArtistResponse.Artist>()

                    var rank: ArtistResponse.Rank? = null
                    when {
                        response.body()?.art?.month != null -> rank = response.body()?.art?.month!!
                        response.body()?.art?.week != null -> rank = response.body()?.art?.week!!
                        response.body()?.art?.day != null -> rank = response.body()?.art?.day!!
                    }

                    when {
                        rank?.all != null -> rank.all!!.forEach{ artists.add(it)}
                        rank?.nacional != null -> rank.nacional!!.forEach{ artists.add(it)}
                        rank?.internacional != null -> rank.internacional!!.forEach{ artists.add(it)}
                    }
                    success(artists)

                } else {
                    failure()
                }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable?) {
                failure()
            }
        })
    }
}