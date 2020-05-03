package com.jlmcdeveloper.buscadordeartistas.data.database



data class User(val name: String, val email: String, val password: String, val date: String, val idUser: String){
    constructor(name: String, password: String): this(name, "", password, "", "")

    companion object{
        const val Table = "users"
        const val ColumnName = "name"
        const val ColumnEmail = "email"
        const val ColumnPassword = "password"
        const val ColumnDate = "date"
    }


    fun getRegister(): Map<String, Any> {
        return hashMapOf(
            "name" to name,
            "email" to email,
            "password" to password,
            "date" to date
        )
    }


}