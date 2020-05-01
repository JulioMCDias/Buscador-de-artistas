package com.jlmcdeveloper.buscadordeartistas.di

import com.jlmcdeveloper.buscadordeartistas.data.ArtistRepository
import com.jlmcdeveloper.buscadordeartistas.ui.artistlist.MainViewModel
import com.jlmcdeveloper.buscadordeartistas.ui.createlogin.CreateLoginViewModel
import com.jlmcdeveloper.buscadordeartistas.ui.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module{
    viewModel { MainViewModel(get() as ArtistRepository) }
}

val loginModule = module{
    viewModel { LoginViewModel(get()) }
}

val newUserModule = module{
    viewModel { CreateLoginViewModel(get()) }
}

val activityModules = listOf(mainModule, loginModule, newUserModule)