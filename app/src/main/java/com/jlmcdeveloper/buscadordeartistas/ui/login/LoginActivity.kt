package com.jlmcdeveloper.buscadordeartistas.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.databinding.ActivityLoginBinding
import com.jlmcdeveloper.buscadordeartistas.ui.createlogin.CreateLoginActivity
import com.jlmcdeveloper.buscadordeartistas.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        btnRegister.setOnClickListener {
            startActivity(Intent(this, CreateLoginActivity::class.java))
        }

        viewModel.success ={
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        viewModel.failure ={
            Toast.makeText(this, "${getString(R.string.error_in_login)} $it", Toast.LENGTH_LONG)
                .show()
        }
    }
}
