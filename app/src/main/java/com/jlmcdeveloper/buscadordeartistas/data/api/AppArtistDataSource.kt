package com.jlmcdeveloper.buscadordeartistas.data.api

import com.jlmcdeveloper.buscadordeartistas.data.model.Artist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppArtistDataSource(private val artistApi: ArtistApi) : ArtistDataSource {

    override fun listArtist(success: (List<Artist>) -> Unit, failure: () -> Unit) {
        val call = artistApi.listArtist(type, period, scope, limit, apikey)

        call.enqueue(object : Callback<ArtistResponse> {
            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) =
                if (response.isSuccessful) {
                    val artists = mutableListOf<Artist>()

                    var rank: Rank? = null
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