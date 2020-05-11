package com.jlmcdeveloper.buscadordeartistas.ui.editlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.databinding.ActivityEditLoginBinding
import com.jlmcdeveloper.buscadordeartistas.utils.DatePickerFragment
import kotlinx.android.synthetic.main.activity_edit_login.*
import org.koin.android.ext.android.inject

class EditLoginActivity : AppCompatActivity() {
    private val viewModel: EditLoginViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityEditLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_edit_login)

        setSupportActionBar(binding.toolbarEditLogin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.success = {
            Toast.makeText(this, getString(R.string.update_user),
                Toast.LENGTH_SHORT).show()
            finish()
        }

        viewModel.failure = {
            Toast.makeText(this, getString(R.string.error_update_user),
                Toast.LENGTH_SHORT).show()
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
