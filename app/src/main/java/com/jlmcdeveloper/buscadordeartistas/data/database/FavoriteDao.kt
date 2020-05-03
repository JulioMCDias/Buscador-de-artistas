package com.jlmcdeveloper.buscadordeartistas.data.database

import com.google.firebase.firestore.FirebaseFirestore
import com.jlmcdeveloper.buscadordeartistas.data.database.model.Favorite
import com.jlmcdeveloper.buscadordeartistas.data.database.model.User

class FavoriteDao(private val db: FirebaseFirestore) {

    fun updateFavorite(idUser: String, favorite: Favorite) {
        val ref = db.collection(User.Table).document(idUser)
            .collection(Favorite.Table).document(favorite.idArtist)
            .set(favorite.getRegister())
    }

    fun getFavorites(
        idUser: String,
        success: (List<Favorite>) -> Unit,
        failure: () -> Unit
    )
    {
        val ref = db.collection(User.Table).document(idUser)
            .collection(Favorite.Table).whereEqualTo(Favorite.ColumnEnable, true)
            .get()
            .addOnSuccessListener { favorites ->
                if (!favorites.isEmpty) {
                    val list = mutableListOf<Favorite>()
                    favorites.documents.forEach {
                        list.add(
                            Favorite(
                                idArtist = it.id,
                                name = it.getString(Favorite.ColumnName)!!,
                                url = it.getString(Favorite.ColumnName)!!,
                                picSmall = it.getString(Favorite.ColumnName)!!,
                                views = it.getString(Favorite.ColumnName)!!,
                                enable = it.getBoolean(Favorite.ColumnName)!!
                            )
                        )
                    }
                    success(list)
                } else
                    failure()
            }
            .addOnFailureListener { failure() }
    }
}