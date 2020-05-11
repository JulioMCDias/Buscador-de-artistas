package com.jlmcdeveloper.buscadordeartistas.ui.createlogin

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.databinding.ActivityCreateLoginBinding
import com.jlmcdeveloper.buscadordeartistas.utils.DatePickerFragment
import kotlinx.android.synthetic.main.activity_create_login.*
import org.koin.android.ext.android.inject

class CreateLoginActivity : AppCompatActivity() {
    private val viewModel: CreateLoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCreateLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_create_login)

        setSupportActionBar(binding.toolbarEditLogin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.success = {
            Toast.makeText(this, getString(R.string.user_create), Toast.LENGTH_SHORT)
                .show()
            finish()
        }

        viewModel.failure = {
            Toast.makeText(this, getString(R.string.error_new_user), Toast.LENGTH_SHORT)
                .show()
        }


        //----- campo data -------
        editTextDate.setOnClickListener {
            val newFragment = DatePickerFragment(editTextDate.text.toString()) { date ->
                editTextDate.setText(date)
            }
            newFragment.show(supportFragmentManager, "datePicker")
        }

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}
