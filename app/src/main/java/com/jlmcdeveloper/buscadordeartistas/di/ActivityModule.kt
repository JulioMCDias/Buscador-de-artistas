package com.jlmcdeveloper.buscadordeartistas.di

import com.jlmcdeveloper.buscadordeartistas.data.Repository
import com.jlmcdeveloper.buscadordeartistas.ui.createlogin.CreateLoginViewModel
import com.jlmcdeveloper.buscadordeartistas.ui.login.LoginViewModel
import com.jlmcdeveloper.buscadordeartistas.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module{
    viewModel { MainViewModel(get() as Repository) }
}

val loginModule = module{
    viewModel { LoginViewModel(get() as Repository) }
}

val newUserModule = module{
    viewModel { CreateLoginViewModel(get() as Repository) }
}

val activityModules = listOf(mainModule, loginModule, newUserModule)