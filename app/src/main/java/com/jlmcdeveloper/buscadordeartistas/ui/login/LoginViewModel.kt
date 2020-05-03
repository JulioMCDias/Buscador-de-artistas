package com.jlmcdeveloper.buscadordeartistas.ui.login

import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.data.UserRepository
import com.jlmcdeveloper.buscadordeartistas.data.database.User

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    fun login(name: String, password: String, success: () -> Unit, failure : (String) -> Unit){
        repository.getUser(User(name = name, password = password), success, failure)
    }
}