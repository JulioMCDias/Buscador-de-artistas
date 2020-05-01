package com.jlmcdeveloper.buscadordeartistas.ui.artistlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jlmcdeveloper.buscadordeartistas.R
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onStart() {
        super.onStart()
        viewModel.load()
    }
}
