package com.jlmcdeveloper.buscadordeartistas.di

import com.jlmcdeveloper.buscadordeartistas.data.ArtistRepository
import com.jlmcdeveloper.buscadordeartistas.data.UserRepository
import org.koin.dsl.module

val dataModule = module {
    //single { Retrofit2() }

    single{ UserRepository() }
    single{ ArtistRepository() }
}


val appModules = listOf(dataModule)