package com.jlmcdeveloper.buscadordeartistas.data

import com.jlmcdeveloper.buscadordeartistas.data.api.ApiEndPoint
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class AppDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when(request.path){
            "/rank.php?type=${ApiEndPoint.type}" +
                    "&period=${ApiEndPoint.period}" +
                    "&scope=${ApiEndPoint.scope}" +
                    "&limit=${ApiEndPoint.limit}" +
                    "&apikey=${ApiEndPoint.apikey}" -> MockResponse().setResponseCode(200).setBody(
                "{\"art\":" +
                        "{\"day\":" +
                        "{\"period\":" +
                        "{\"year\":\"2020\",\"day\":\"15\",\"month\":\"05\"}," +
                        "\"internacional\":[" +
                        "{\"id\":\"${id}\",\"name\":\"${name}\",\"url\":\"${url}\",\"pic_small\":\"${pic_small}\",\"pic_medium\":\"https://s2.vagalume.com/dua-lipa/images/dua-lipa.jpg\",\"uniques\":\"0\",\"views\":\"${views}\",\"rank\":\"0.0\"}," +
                        "{\"id\":\"3ade68b7g30dd1ea3\",\"name\":\"Ed Sheeran\",\"url\":\"https://www.vagalume.com.br/ed-sheeran/\",\"pic_small\":\"https://s2.vagalume.com/ed-sheeran/images/profile.jpg\",\"pic_medium\":\"https://s2.vagalume.com/ed-sheeran/images/ed-sheeran.jpg\",\"uniques\":\"0\",\"views\":\"2965\",\"rank\":\"0.0\"}]" +
                        "}}}"
            )

            else -> MockResponse().setResponseCode(404)
        }
    }

    companion object {
        const val size = 2
        const val id = "3ade68b7ga71d2ea3"
        const val name = "Dua Lipa"
        const val pic_small = "https://s2.vagalume.com/dua-lipa/images/profile.jpg"
        const val url = "https://www.vagalume.com.br/dua-lipa/"
        const val views = "3161"
    }

}