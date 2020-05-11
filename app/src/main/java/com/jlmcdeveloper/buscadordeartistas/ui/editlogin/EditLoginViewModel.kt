package com.jlmcdeveloper.buscadordeartistas.ui.editlogin

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.data.Repository
import com.jlmcdeveloper.buscadordeartistas.data.database.model.User
import com.jlmcdeveloper.buscadordeartistas.utils.validateCampEmpty
import com.jlmcdeveloper.buscadordeartistas.utils.validateEmail


class EditLoginViewModel(private val repository: Repository, private val context: Context) : ViewModel() {
    val loadingVisibility = MutableLiveData(false)

    val editName = MutableLiveData<String>(repository.user?.name)
    val editEmail = MutableLiveData<String>(repository.user?.email)
    val editPassword = MutableLiveData<String>(repository.user?.password)
    val editDate = MutableLiveData<String>(repository.user?.date)

    val textErrorName = MutableLiveData<String?>()
    val textErrorEmail = MutableLiveData<String?>()
    val textErrorPassword = MutableLiveData<String?>()
    val textErrorDate = MutableLiveData<String?>()


    var success: (() -> Unit)? = null
    var failure: (() -> Unit)? = null


    fun btnSave() {
        loadingVisibility.postValue(true)
        if (validateCampEmpty(editName, textErrorName, context.getString(R.string.campNull)) and
            validateCampEmpty(editPassword, textErrorPassword, context.getString(R.string.campNull)) and
            validateCampEmpty(editDate, textErrorDate, context.getString(R.string.campNull)) and
            validateEmail(editEmail, textErrorEmail,
                context.getString(R.string.campNull),
                context.getString(R.string.email_valid)))
        {

            val user = User(
                editName.value!!,
                editEmail.value!!,
                editPassword.value!!,
                editDate.value!!,
                repository.user!!.idUser)


            repository.updateUser(user, {
                loadingVisibility.postValue(false)
                success?.let { it() }
            }, {
                loadingVisibility.postValue(false)
                failure?.let { it() }
            })
        }else
            loadingVisibility.postValue(false)
    }
}