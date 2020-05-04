package com.jlmcdeveloper.buscadordeartistas.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.lifecycle.Observer
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.ui.editlogin.EditLoginActivity
import com.jlmcdeveloper.buscadordeartistas.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by inject()
    private var adapter: ArtistAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMain)
        adapter =  ArtistAdapter(ArrayList(), viewModel)
        recyclerView.adapter = adapter

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
            R.id.menu_login_edit -> {
                startActivity(Intent(this, EditLoginActivity::class.java))
                return true
            }
            R.id.menu_logout -> {
                startActivity(Intent(this, LoginActivity::class.java))
                viewModel.logout()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_arts, menu)

        //---- btn favoritos ----
        menu!!.forEach { item ->
            if(item.itemId == R.id.menu_favorite) {
                val checkBoxFavorite = item.actionView as CheckBox
                checkBoxFavorite.setOnClickListener {
                    viewModel.listFavorites(checkBoxFavorite.isChecked)
                }
                // ------ update -----
                viewModel.favorite.observe(this, Observer {
                    checkBoxFavorite.isChecked = it
                })
            }
            if(item.itemId == R.id.menu_search) {
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener( object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String): Boolean{
                        return false
                    }
                    override fun onQueryTextChange(newText: String): Boolean{
                        adapter?.filter?.filter(newText)
                        return false
                    }
                })
            }
        }

        return super.onCreateOptionsMenu(menu)
    }
}
