package com.jlmcdeveloper.buscadordeartistas.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorText")
fun errorText(textInputLayout: TextInputLayout, textInfo: String?) {
        textInputLayout.error = textInfo
}