package com.jlmcdeveloper.buscadordeartistas.data

import androidx.test.core.app.ApplicationProvider
import com.jlmcdeveloper.buscadordeartistas.di.appModulesTest
import org.junit.Assert.assertEquals
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

class RepositoryTest : KoinTest {

    private val repository: Repository by inject()
    private val mockWebServer: MockWebServer by inject()

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(appModulesTest)
        }
        mockWebServer.dispatcher = AppDispatcher()
    }

    @After
    fun tearDown() {
        stopKoin()
        mockWebServer.shutdown()
    }



    //------------ api --------------
    @Test
    fun listArtistTest_Success(){
        repository.listArtist({
            assertEquals(it.size, AppDispatcher.size)
            val artist = it[0]

            assertEquals(artist.id, AppDispatcher.id)
            assertEquals(artist.name, AppDispatcher.name)
            assertEquals(artist.pic_small, AppDispatcher.pic_small)
            assertEquals(artist.url, AppDispatcher.url)
            assertEquals(artist.views, AppDispatcher.views)
        }, {

        })
    }

    //------------ database -----------
    @Test
    fun getUser() {

    }

    @Test
    fun logout() {

    }
}