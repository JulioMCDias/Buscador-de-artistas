package com.jlmcdeveloper.buscadordeartistas.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlmcdeveloper.buscadordeartistas.data.ArtistRepository
import com.jlmcdeveloper.buscadordeartistas.data.database.model.Favorite
import com.jlmcdeveloper.buscadordeartistas.data.model.ArtistItem

class MainViewModel(private val repository: ArtistRepository) : ViewModel() {
    var artists = MutableLiveData<MutableList<ArtistItem>>()
    val loadingVisibility = MutableLiveData(false)
    val message = MutableLiveData<String>()

    // --------------- lista de artistas ------------
    fun loadListArtist() {
        loadingVisibility.postValue(true)

        // ---- get lista da api ---
        repository.listArtist({ items ->
            val listArt = ArrayList<ArtistItem>()
            items.forEach {
                listArt.add(ArtistItem(it, false))
            }

            getListFavorites(listArt)

        }, { error("erro ao carregar a lista de artistas musicais")})
    }

    private fun getListFavorites(artistsApi: ArrayList<ArtistItem>){
        val listArtsFav = ArrayList<ArtistItem>()

        repository.getListFavorites( { favorites ->
            favorites.forEach { listArtsFav.add(ArtistItem(it)) }

            for (item in listArtsFav){
                if(!artistsApi.none { it.idArtist.equals(item.idArtist) })
                    artistsApi.filter { it.idArtist.equals(item.idArtist) }[0].favorit = item.favorit
                else
                    artistsApi.add(item)
            }
            setList(artistsApi)

        }, {
            error("erro ao buscar os favoritos")
            setList(artistsApi)
        })
    }

    private fun setList(artist: ArrayList<ArtistItem>) {
        artists.postValue(artist)
        loadingVisibility.postValue(false)
    }


    private fun error(info: String){
        loadingVisibility.postValue(false)
        message.postValue(info)
    }
    //---------





    fun updateArtistFavorite(artist: ArtistItem) {
        repository.updateFavorites(Favorite(artist))
    }


    fun logout() {
        repository.logout()
    }
}