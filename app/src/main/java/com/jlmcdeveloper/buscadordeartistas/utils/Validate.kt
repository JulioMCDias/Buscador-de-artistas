package com.jlmcdeveloper.buscadordeartistas.utils

import android.util.Patterns
import androidx.lifecycle.MutableLiveData


fun validateCampEmpty(editText: MutableLiveData<String>,
                      textError: MutableLiveData<String?>,
                      info: String) : Boolean{
    val text = editText.value?.trim() ?: ""
    return if(text.isEmpty()) {
            textError.postValue(info)
            false
        }else{
            textError.postValue(null)
            true
        }
}

fun validateEmail(editText: MutableLiveData<String>,
                  textError: MutableLiveData<String?>,
                  infoEmpty: String,
                  infoEmail: String) : Boolean{

    val email = editText.value?.trim() ?: ""
    return if(email.isEmpty()) {
        textError.postValue(infoEmpty)
        false
    } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        textError.postValue(infoEmail)
        false
    }else{
        textError.postValue(null)
        true
    }
}