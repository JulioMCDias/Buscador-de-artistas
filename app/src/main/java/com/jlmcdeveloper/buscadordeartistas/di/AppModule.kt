package com.jlmcdeveloper.buscadordeartistas.di

import com.google.firebase.firestore.FirebaseFirestore
import com.jlmcdeveloper.buscadordeartistas.data.Repository
import com.jlmcdeveloper.buscadordeartistas.data.api.ApiEndPoint
import com.jlmcdeveloper.buscadordeartistas.data.api.ApiRestServer
import com.jlmcdeveloper.buscadordeartistas.data.api.AppArtistDataSource
import com.jlmcdeveloper.buscadordeartistas.data.database.FavoriteDao
import com.jlmcdeveloper.buscadordeartistas.data.database.HelperDatabase
import com.jlmcdeveloper.buscadordeartistas.data.database.UserDao
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    val cacheSize: Long = 10 * 1024 * 1024

    single { Cache(androidContext().cacheDir, cacheSize) }
    single { OkHttpClient.Builder().cache(get()).build() }
    single {
        Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ApiEndPoint.url)
        .client(get() as OkHttpClient)
        .build()
    }
    single { (get() as Retrofit).create(ApiRestServer::class.java) }
    single { AppArtistDataSource(get()) }

}

val databaseModule = module {
    //Access a Cloud Firestore instance
    single { FirebaseFirestore.getInstance() }

    single { FavoriteDao(get()) }
    single { UserDao(get()) }

    single { HelperDatabase(get(), get()) }
}

val repositoryModule = module {
    single{ Repository(get() as AppArtistDataSource, get() as HelperDatabase) }
}


val appModules = apiModule + databaseModule + repositoryModule