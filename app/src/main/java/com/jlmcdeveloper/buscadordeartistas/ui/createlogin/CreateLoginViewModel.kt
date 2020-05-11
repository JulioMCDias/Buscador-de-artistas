package com.jlmcdeveloper.buscadordeartistas.ui.createlogin

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.data.UserRepository
import com.jlmcdeveloper.buscadordeartistas.data.database.model.User
import com.jlmcdeveloper.buscadordeartistas.utils.validateCampEmpty
import com.jlmcdeveloper.buscadordeartistas.utils.validateEmail

class CreateLoginViewModel(private val repository: UserRepository, private val context: Context) : ViewModel() {
    val loadingVisibility = MutableLiveData(false)

    val editName = MutableLiveData<String>()
    val editEmail = MutableLiveData<String>()
    val editPassword = MutableLiveData<String>()
    val editDate = MutableLiveData<String>()

    val textErrorName = MutableLiveData<String?>()
    val textErrorEmail = MutableLiveData<String?>()
    val textErrorPassword = MutableLiveData<String?>()
    val textErrorDate = MutableLiveData<String?>()


    var success: (() -> Unit)? = null
    var failure: (() -> Unit)? = null


    fun btnRegister() {
        loadingVisibility.postValue(true)
        if (validateCampEmpty(editName, textErrorName, context.getString(R.string.campNull)) and
            validateCampEmpty(
                editPassword,
                textErrorPassword,
                context.getString(R.string.campNull)
            ) and
            validateCampEmpty(editDate, textErrorDate, context.getString(R.string.campNull)) and
            validateEmail(
                editEmail, textErrorEmail,
                context.getString(R.string.campNull),
                context.getString(R.string.email_valid)
            )
        ) {


            val user = User(
                editName.value!!,
                editEmail.value!!,
                editPassword.value!!,
                editDate.value!!,
                ""
            )
            repository.createUser(user, {
                loadingVisibility.postValue(false)
                success?.invoke()
            }, {
                loadingVisibility.postValue(false)
                failure?.let { it() }
            })

        } else loadingVisibility.postValue(false)
    }
}