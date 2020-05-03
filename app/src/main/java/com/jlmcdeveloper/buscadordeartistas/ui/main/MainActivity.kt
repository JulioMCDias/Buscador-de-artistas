package com.jlmcdeveloper.buscadordeartistas.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem
import com.jlmcdeveloper.buscadordeartistas.ui.editlogin.EditLoginActivity
import com.jlmcdeveloper.buscadordeartistas.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMain)


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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_login_edit ->
                startActivity( Intent(this, EditLoginActivity::class.java))
            R.id.menu_logout -> {
                startActivity(Intent(this, LoginActivity::class.java))
                viewModel.logout()
                finish()
            }

        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_arts, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun loading(enable: Boolean){
        pb_loading.visibility = if(enable) View.VISIBLE else View.GONE
        layout_loading.visibility = if(enable) View.VISIBLE else View.GONE
    }
}