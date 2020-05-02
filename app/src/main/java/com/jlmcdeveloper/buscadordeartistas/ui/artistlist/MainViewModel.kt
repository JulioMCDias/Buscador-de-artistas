package com.jlmcdeveloper.buscadordeartistas.ui.artistlist

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.data.ArtistRepository
import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem

class MainViewModel(private val repository: ArtistRepository): ViewModel() {
    var artists = MutableLiveData<MutableList<ArtistItem>>()
    val loadingVisibility = ObservableBoolean(false)
    val message = ObservableField<String>()

    fun load(){
        loadingVisibility.set(true)
        message.set("")

        repository.listArtist({ items ->
            val listArtist = ArrayList<ArtistItem>()

            items.forEach{
                listArtist.add(
                    ArtistItem(it.name, it.url, it.pic_small, it.views.toString(), false))
            }
            artists.postValue(listArtist)
            loadingVisibility.set(false)
        },{
            // error implement
            message.set("Erro")
            loadingVisibility.set(false)
        })
    }




}