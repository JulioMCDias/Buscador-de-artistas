package com.jlmcdeveloper.buscadordeartistas.ui.editlogin

import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.data.Repository
import com.jlmcdeveloper.buscadordeartistas.data.database.model.User


class EditLoginViewModel(private val repository: Repository) : ViewModel(){

    fun getName() : String? = repository.user?.name
    fun getEmail() : String? = repository.user?.email
    fun getPassword() : String? = repository.user?.password
    fun getDate() : String? = repository.user?.date

    fun btnSave(
        name: String,
        email: String,
        password: String,
        date: String,
        success: () -> Unit,
        failure: () -> Unit)
    {
        val user = User(
            name,
            email,
            password,
            date,
            repository.user?.idUser!!
        )

        repository.updateUser(user, success, failure)
    }
}