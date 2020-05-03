package com.jlmcdeveloper.buscadordeartistas.data.database

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class HelperDatabase(private val db: FirebaseFirestore) {


    fun createUser(user: User, success : (String) -> Unit, failure: () -> Unit){
        val ref = db.collection(User.Table)
        ref.whereEqualTo(User.ColumnName, user.name)
            .whereEqualTo(User.ColumnEmail, user.email)
            .get()
            .addOnSuccessListener { documents ->
                if(documents.isEmpty)
                    addUser(user, success, failure)
                else
                    failure()
            }
            .addOnFailureListener {
                failure()
            }

    }

    private fun addUser(user: User, success : (String) -> Unit,  failure: () -> Unit){
        db.collection(User.Table)
            .add(user.getRegister())
            .addOnSuccessListener { documentReference ->
                success(documentReference.id)

            }.addOnFailureListener {
                failure()
            }
    }


    fun getUser(user: User, success : (User) -> Unit, failure: (String) -> Unit){
        Log.d("Aqui", user.name + user.password)
        val ref = db.collection(User.Table)
        ref.whereEqualTo(User.ColumnName, user.name)
            .whereEqualTo(User.ColumnPassword, user.password)
            .get()
            .addOnSuccessListener { documents ->
                if(!documents.isEmpty) {
                   val us = documents.documents[0]
                    success( User(
                        name =  us.getString(User.ColumnName)!!,
                        email =  us.getString(User.ColumnEmail)!!,
                        password = us.getString(User.ColumnPassword)!!,
                        date = us.getString(User.ColumnDate)!!
                    ))
                }else
                    failure("usuario não cadastrado")
            }
            .addOnFailureListener {
                failure(it.toString())
            }
    }

    fun updateUser(user: User, success : () -> Unit,  failure: () -> Unit){
        val ref = db.collection(User.Table)

        ref.document(user.idUser).set(user)
            .addOnSuccessListener { success() }
            .addOnFailureListener { failure() }
    }



    fun addFavorites(){

    }

    fun getFavorites(){

    }

    fun removeFavorites(){

    }
}