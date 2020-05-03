package com.jlmcdeveloper.buscadordeartistas.ui.editlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.utils.validateCamp
import com.jlmcdeveloper.buscadordeartistas.utils.validateEmail
import kotlinx.android.synthetic.main.activity_edit_login.*
import org.koin.android.ext.android.inject

class EditLoginActivity : AppCompatActivity() {
    private val viewModel: EditLoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_login)

        setSupportActionBar(toolbarEditLogin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loading(false);
        setTexts()

        btnSave.setOnClickListener {
            loading(true)

            if(validateCamp(editTextName, textInputLayoutName, getString(R.string.campNull)) and
                validateCamp(editTextPassword, textInputLayoutPassword, getString(R.string.campNull)) and
                validateCamp(editTextDate, textInputLayoutDate, getString(R.string.campNull)) and
                validateEmail(editTextEmail, textInputLayoutEmail, getString(R.string.campNull),
                    getString(R.string.email_valid))
            ) {

                viewModel.btnSave(
                    editTextName.text.toString(),
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString(),
                    editTextDate.text.toString(),
                    {
                        loading(false)
                        Toast.makeText(this, getString(R.string.update_user), Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }, {
                        loading(false)
                        Toast.makeText(this, getString(R.string.error_update_user), Toast.LENGTH_SHORT)
                            .show()
                    })
            }
        }
    }

    private fun setTexts(){
        editTextName.setText(viewModel.getName())
        editTextEmail.setText(viewModel.getEmail())
        editTextPassword.setText(viewModel.getPassword())
        editTextDate.setText(viewModel.getDate())
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
