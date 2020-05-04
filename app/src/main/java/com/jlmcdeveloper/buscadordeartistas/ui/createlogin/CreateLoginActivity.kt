package com.jlmcdeveloper.buscadordeartistas.ui.createlogin

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.utils.DatePickerFragment
import com.jlmcdeveloper.buscadordeartistas.utils.validateCamp
import com.jlmcdeveloper.buscadordeartistas.utils.validateEmail
import kotlinx.android.synthetic.main.activity_create_login.*
import org.koin.android.ext.android.inject

class CreateLoginActivity : AppCompatActivity() {
    private val viewModel: CreateLoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_login)

        setSupportActionBar(toolbarEditLogin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loading(false)

        btnSave.setOnClickListener {
            if(validateCamp(editTextName, textInputLayoutName, getString(R.string.campNull)) and
                validateCamp(editTextPassword, textInputLayoutPassword, getString(R.string.campNull)) and
                validateCamp(editTextDate, textInputLayoutDate, getString(R.string.campNull)) and
                validateEmail(editTextEmail, textInputLayoutEmail, getString(R.string.campNull),
                    getString(R.string.email_valid))) {

                loading(true)

                viewModel.btnRegister(
                    editTextName.text.toString(),
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString(),
                    editTextDate.text.toString(),
                    {
                        loading(false)
                        Toast.makeText(this, getString(R.string.user_create), Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }, {
                        loading(false)
                        Toast.makeText(this, getString(R.string.error_new_user), Toast.LENGTH_SHORT)
                            .show()
                    })
            }
        }

        //----- campo data -------
        editTextDate.setOnClickListener {
            val newFragment = DatePickerFragment(editTextDate.text.toString()) { date ->
                editTextDate.setText(date)
            }
            newFragment.show(supportFragmentManager, "datePicker")
        }

    }

    private fun loading(enable: Boolean){
        pb_loading.visibility = if(enable) View.VISIBLE else View.GONE
        layout_loading.visibility = if(enable) View.VISIBLE else View.GONE
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}
