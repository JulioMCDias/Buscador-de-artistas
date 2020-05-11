package com.jlmcdeveloper.buscadordeartistas.ui.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.data.UserRepository
import com.jlmcdeveloper.buscadordeartistas.data.database.model.User
import com.jlmcdeveloper.buscadordeartistas.utils.validateCampEmpty

class LoginViewModel(private val repository: UserRepository, private val context: Context): ViewModel() {
    val loadingVisibility = MutableLiveData(false)

    val editName = MutableLiveData<String>()
    val editPassword = MutableLiveData<String>()

    val textErrorName = MutableLiveData<String?>()
    val textErrorPassword = MutableLiveData<String?>()

    var success: (() -> Unit)? = null
    var failure: ((String) -> Unit)? = null

    fun login(){
        loadingVisibility.postValue(true)
        if(validateCampEmpty(editName, textErrorName, context.getString(R.string.campNull)) and
            validateCampEmpty(editPassword, textErrorPassword, context.getString(R.string.campNull)))
        {
            repository.getUser( User(editName.value!!, editPassword.value!!),
                {
                    loadingVisibility.postValue(false)
                    success?.invoke()
                }, {
                    loadingVisibility.postValue(false)
                    failure?.invoke(it)
                })
        }else loadingVisibility.postValue(false)
    }
}