package com.jlmcdeveloper.buscadordeartistas.ui.createlogin

import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.data.Repository
import com.jlmcdeveloper.buscadordeartistas.data.UserRepository
import com.jlmcdeveloper.buscadordeartistas.data.database.User

class CreateLoginViewModel(private val repository: UserRepository) : ViewModel() {

    fun btnRegister(
        name: String,
        email: String,
        password: String,
        date: String,
        success: () -> Unit,
        failure: () -> Unit)
    {
        val user = User(name, email, password, date, "")
        repository.createUser(user, success, failure)
    }

}