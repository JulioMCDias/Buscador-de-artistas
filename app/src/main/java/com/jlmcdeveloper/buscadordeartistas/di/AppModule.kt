package com.jlmcdeveloper.buscadordeartistas.di

import com.jlmcdeveloper.buscadordeartistas.data.ArtistRepository
import com.jlmcdeveloper.buscadordeartistas.data.UserRepository
import com.jlmcdeveloper.buscadordeartistas.data.api.AppArtistDataSource
import com.jlmcdeveloper.buscadordeartistas.data.api.ArtistApi
import com.jlmcdeveloper.buscadordeartistas.data.api.url
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    val cacheSize: Long = 10 * 1024 * 1024

    single { Cache(androidContext().cacheDir, cacheSize) }
    single { OkHttpClient.Builder().cache(get()).build() }
    single {
        Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .client(get() as OkHttpClient)
        .build()
    }
    single { (get() as Retrofit).create(ArtistApi::class.java) }
    single { AppArtistDataSource(get()) }

    single{ UserRepository() }
    single{ ArtistRepository(get() as AppArtistDataSource) }
}


val appModules = listOf(dataModule)