package com.jlmcdeveloper.buscadordeartistas.data.api


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ArtistApi {

    @FormUrlEncoded
    @POST("/rank.php")
    fun listArtist(@Field("type") type: String,
                   @Field("period") period: String,
                   @Field("scope") scope: String,
                   @Field("limit") limit: Int,
                   @Field("apikey") apikey: String):
            Call<ArtistResponse>

}