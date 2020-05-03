package com.jlmcdeveloper.buscadordeartistas.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.ui.editlogin.EditLoginActivity
import com.jlmcdeveloper.buscadordeartistas.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMain)
        recyclerView.adapter = ArtistAdapter(ArrayList(), viewModel)

        // ------ update da lista -------
        viewModel.artists.observe(this, Observer {
                (recyclerView.adapter as ArtistAdapter)
                    .updateItems(ArrayList(it!!.toMutableList()))
        })

        // ------ animação de carregamento ----------
        viewModel.loadingVisibility.observe(this, Observer {
            pb_loading.visibility = if(it) View.VISIBLE else View.GONE
            layout_loading.visibility = if(it) View.VISIBLE else View.GONE
            (recyclerView.adapter as ArtistAdapter).notifyDataSetChanged()
        })

        // ----- mensagem de erro -------
        viewModel.message.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }


    override fun onStart() {
        super.onStart()
        viewModel.loadListArtist()
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
}
