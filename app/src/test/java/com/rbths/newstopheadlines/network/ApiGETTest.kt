package com.rbths.newstopheadlines.network

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.rbths.newstopheadlines.model.ArticlesResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class ApiGETTest {
    private lateinit var myApiService: ApiGET

    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        myApiService = retrofit.create(ApiGET::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetSources_isSuccessful() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
                {
                    "status": "ok",
                    "totalResults": 2,
                    "articles": [
                        {
                            "source": {
                                "id": "bbc-news",
                                "name": "BBC News"
                            },
                            "author": "BBC News",
                            "title": "Alex Murdaugh jurors to visit Moselle estate in murder trial",
                            "description": "Mr Murdaugh's wife and son were found dead near dog kennels on the 1,700-acre piece of land in 2021",
                            "url": "http://www.bbc.co.uk/news/world-us-canada-64813181",
                            "urlToImage": "https://ichef.bbci.co.uk/news/1024/branded_news/12CFB/production/_128815077_bf6b63f3dca5b837fa09f1405c3b0581ed5c3c340_0_6172_41991000x680.jpg",
                            "publishedAt": "2023-03-01T14:22:27.0396019Z",
                            "content": "Jurors in Alex Murdaugh's murder trial will get to visit the family's South Carolina estate as part of their deliberations.\r\nLawyers for Mr Murdaugh requested that the jury be able to visit the prope… [+1877 chars]"
                        }
                           ,
                        {
                            "source": {
                                "id": "bbc-news",
                                "name": "BBC News"
                            },
                            "author": "BBC News"
                        }
                    ]
                }
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)

        val response = myApiService.getSources().execute()

        assertThat(response.isSuccessful).isTrue()

        assertThat(response.body()!!).isInstanceOf(ArticlesResponse::class.java)



    }

    @Test
    fun testGetSourcesWIth_serverError() {
        val mockResponse = MockResponse()
            .setResponseCode(500)
            .setBody("""
                {
                    "status": "500 Server Error"
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)

        val response = myApiService.getSources().execute()

        assertThat(response.isSuccessful).isFalse()
        assertThat(response.errorBody()).isNotNull()
    }

}