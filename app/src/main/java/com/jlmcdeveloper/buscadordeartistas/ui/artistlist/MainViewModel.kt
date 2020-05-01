package com.jlmcdeveloper.buscadordeartistas.ui.artistlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.data.ArtistRepository
import com.jlmcdeveloper.buscadordeartistas.data.model.Artist

class MainViewModel(private val repository: ArtistRepository): ViewModel() {
    var artists = MutableLiveData<MutableList<Artist>>()


    fun load(){
        repository.listArtist({ items ->
            artists.postValue(items.toMutableList())
            Log.d("Artists", items.toString())
        },{
            // error implement
        })
    }




}