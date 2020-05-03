package com.jlmcdeveloper.buscadordeartistas

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.jlmcdeveloper.buscadordeartistas.di.activityModules
import com.jlmcdeveloper.buscadordeartistas.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        val moduleList = appModules + activityModules

        startKoin{
            // declare used Android context
            androidContext(this@AppApplication)
            // declare modules
            modules(moduleList)
        }
    }
}