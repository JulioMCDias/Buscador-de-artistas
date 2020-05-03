package com.jlmcdeveloper.buscadordeartistas.data
import com.jlmcdeveloper.buscadordeartistas.data.database.User

interface UserRepository {

    fun createUser(user: User, success : (String) -> Unit, failure: () -> Unit)
    fun getUser(user: User, success : () -> Unit, failure: (String) -> Unit)
    fun updateUser(user: User, success : () -> Unit,  failure: () -> Unit)
    fun logout()

}