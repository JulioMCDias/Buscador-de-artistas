package com.jlmcdeveloper.buscadordeartistas.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.ui.createlogin.CreateLoginActivity
import com.jlmcdeveloper.buscadordeartistas.ui.main.MainActivity
import com.jlmcdeveloper.buscadordeartistas.utils.validateCamp
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loading(false)

        btnSave.setOnClickListener {
            startActivity(Intent(this, CreateLoginActivity::class.java))
        }

        btnLogin.setOnClickListener {
            loading(true)

            if(validateCamp(editTextName, textInputLayoutName, getString(R.string.campNull)) and
                validateCamp(editTextPassword, textInputLayoutPassword, getString(R.string.campNull)))
            {
                viewModel.login(editTextName.text.toString(), editTextPassword.text.toString(), {
                    loading(false)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },{
                    loading(false)
                    Toast.makeText(this, getString(R.string.error_in_login) + it, Toast.LENGTH_LONG)
                        .show()
                })
            }
        }

    }

    private fun loading(enable: Boolean){
        pb_loading.visibility = if(enable) View.VISIBLE else View.GONE
        layout_loading.visibility = if(enable) View.VISIBLE else View.GONE
    }
}
