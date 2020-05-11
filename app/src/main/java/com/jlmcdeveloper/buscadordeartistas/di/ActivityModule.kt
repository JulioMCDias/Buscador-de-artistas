package com.jlmcdeveloper.buscadordeartistas.di

import com.jlmcdeveloper.buscadordeartistas.data.Repository
import com.jlmcdeveloper.buscadordeartistas.ui.createlogin.CreateLoginViewModel
import com.jlmcdeveloper.buscadordeartistas.ui.editlogin.EditLoginViewModel
import com.jlmcdeveloper.buscadordeartistas.ui.login.LoginViewModel
import com.jlmcdeveloper.buscadordeartistas.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module{
    viewModel { MainViewModel(get() as Repository) }
}

val loginModule = module{
    viewModel { LoginViewModel(get() as Repository, androidContext()) }
}

val newUserModule = module{
    viewModel { CreateLoginViewModel(get() as Repository, androidContext()) }
}

val editLoginModule = module {
    viewModel { EditLoginViewModel(get() as Repository, androidContext()) }
}

val activityModules = listOf(mainModule, loginModule, newUserModule, editLoginModule)