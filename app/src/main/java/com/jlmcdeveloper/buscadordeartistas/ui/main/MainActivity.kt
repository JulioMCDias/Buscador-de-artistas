package com.jlmcdeveloper.buscadordeartistas.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = ArtistAdapter(ArrayList())

        val observer = Observer<MutableList<ArtistItem>> { t ->
                (recyclerView.adapter as ArtistAdapter)
                    .updateItems(ArrayList(t!!.toMutableList()))
            loading(false)
            }
        viewModel.artists.observe(this, observer)


    }


    override fun onStart() {
        super.onStart()
        viewModel.load()
        loading(true)
    }

    private fun loading(enable: Boolean){
        pb_loading.visibility = if(enable) View.VISIBLE else View.GONE
        layout_loading.visibility = if(enable) View.VISIBLE else View.GONE
    }
}
