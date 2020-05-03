package com.jlmcdeveloper.buscadordeartistas.utils

import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputLayout



internal fun validateCamp(editText: AppCompatEditText, layoutText: TextInputLayout, info: String) : Boolean{
    return if(editText.text.toString().trim().isEmpty()) {
        layoutText.error = info
        false
    }else{
        layoutText.error = null
        true
    }
}


internal fun validateEmail(editText: AppCompatEditText, layoutText: TextInputLayout, infoVoid: String, info: String) : Boolean{
    val email = editText.text.toString().trim()
    return if(editText.text.toString().trim().isEmpty()) {
        layoutText.error = infoVoid
        false
    } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        layoutText.error = info
        false
    }else{
        layoutText.error = null
        true
    }
}