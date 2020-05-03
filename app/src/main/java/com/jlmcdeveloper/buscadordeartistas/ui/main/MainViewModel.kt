package com.jlmcdeveloper.buscadordeartistas.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.data.ArtistRepository
import com.jlmcdeveloper.buscadordeartistas.data.database.model.Favorite
import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem

class MainViewModel(private val repository: ArtistRepository) : ViewModel() {
    var artists = MutableLiveData<MutableList<ArtistItem>>()
    val loadingVisibility = MutableLiveData(false)

    fun loadListArtist() {
        loadingVisibility.postValue(true)

        repository.listArtist({ items ->
            val listArtist = ArrayList<ArtistItem>()

            items.forEach {
                listArtist.add(ArtistItem(it, false))
            }
            artists.postValue(listArtist)
            loadingVisibility.postValue(false)

        }, { loadingVisibility.postValue(false) })
    }


    fun updateArtistFavorite(artist: ArtistItem) {
        repository.updateFavorites(Favorite(artist))
    }


    fun logout() {
        repository.logout()
    }
}