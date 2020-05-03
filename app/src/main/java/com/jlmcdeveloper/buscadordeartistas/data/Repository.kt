package com.jlmcdeveloper.buscadordeartistas.data

import com.jlmcdeveloper.buscadordeartistas.data.api.ArtistDataSource
import com.jlmcdeveloper.buscadordeartistas.data.api.ArtistResponse
import com.jlmcdeveloper.buscadordeartistas.data.database.HelperDatabase
import com.jlmcdeveloper.buscadordeartistas.data.database.User

class Repository(private val artistDataSource: ArtistDataSource, private val db: HelperDatabase) : ArtistRepository, UserRepository{
    var artists: List<ArtistResponse.Artist>? = null
    var user: User? = null

    override fun listArtist(success: (List<ArtistResponse.Artist>) -> Unit, failure: () -> Unit) {
        if(artists == null) {
            artistDataSource.listArtist({ items ->
                artists = items
                success(items)
            }, failure)

        }else
            success(artists!!)
    }





    // --------- usuarios ------------
    override fun createUser(user: User, success : () -> Unit, failure: () -> Unit) {
        db.createUser(user, { success() }, failure)
    }

    override fun getUser(user: User, success: () -> Unit, failure: (String) -> Unit) {
        db.getUser(user, {
            this.user = it
            success()
        }, failure)
    }

    override fun updateUser(user: User, success: () -> Unit, failure: () -> Unit) {
        db.updateUser(user, {
            this.user = user
            success()
        }, failure)
    }

    override fun logout() {
        user = null
    }


    // -----------------

}