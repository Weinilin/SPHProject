package com.example.sphproject

import com.example.sphproject.Models.DataResponse
import com.example.sphproject.Models.NetworkHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Before
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import org.mockito.Mockito
import java.io.IOException

import org.junit.Assert.assertEquals
import org.junit.Test


class NetworkUnitTest {
    private var mockWebServer = MockWebServer()
    private val key = "5"
    private var source = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f"
    private lateinit var apiService: DataApi

    @Before
    fun setup() {
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(NetworkHelper().provideOkHttpClient())
            .build()
            .create(DataApi::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessApi() {
        val successResponse =  MockResponse().setBody(
            "{\n" +
                    "  \"help\": \"https://data.gov.sg/api/3/action/help_show?name=datastore_search\",\n" +
                    "  \"success\": true,\n" +
                    "  \"result\": {\n" +
                    "    \"resource_id\": \"a807b7ab-6cad-4aa6-87d0-e283a7353a0f\",\n" +
                    "    \"fields\": [\n" +
                    "      {\n" +
                    "        \"type\": \"int4\",\n" +
                    "        \"id\": \"_id\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"type\": \"text\",\n" +
                    "        \"id\": \"quarter\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"type\": \"numeric\",\n" +
                    "        \"id\": \"volume_of_mobile_data\"\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"records\": [\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.000384\",\n" +
                    "        \"quarter\": \"2004-Q3\",\n" +
                    "        \"_id\": 1\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.000543\",\n" +
                    "        \"quarter\": \"2004-Q4\",\n" +
                    "        \"_id\": 2\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.00062\",\n" +
                    "        \"quarter\": \"2005-Q1\",\n" +
                    "        \"_id\": 3\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.000634\",\n" +
                    "        \"quarter\": \"2005-Q2\",\n" +
                    "        \"_id\": 4\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.000718\",\n" +
                    "        \"quarter\": \"2005-Q3\",\n" +
                    "        \"_id\": 5\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"_links\": {\n" +
                    "      \"start\": \"/api/action/datastore_search?limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\",\n" +
                    "      \"next\": \"/api/action/datastore_search?offset=5&limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\"\n" +
                    "    },\n" +
                    "    \"limit\": 5,\n" +
                    "    \"total\": 59\n" +
                    "  }\n" +
                    "}"
        )
        mockWebServer.enqueue(successResponse)
        // Act
        val product = apiService.getMobileVol(source, key).execute()

        assertEquals(product.isSuccessful, true)
    }

    @Test
    fun testParse() {
        val successResponse = MockResponse().setBody(
            "{\n" +
                    "  \"help\": \"https://data.gov.sg/api/3/action/help_show?name=datastore_search\",\n" +
                    "  \"success\": true,\n" +
                    "  \"result\": {\n" +
                    "    \"resource_id\": \"a807b7ab-6cad-4aa6-87d0-e283a7353a0f\",\n" +
                    "    \"fields\": [\n" +
                    "      {\n" +
                    "        \"type\": \"int4\",\n" +
                    "        \"id\": \"_id\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"type\": \"text\",\n" +
                    "        \"id\": \"quarter\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"type\": \"numeric\",\n" +
                    "        \"id\": \"volume_of_mobile_data\"\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"records\": [\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.000384\",\n" +
                    "        \"quarter\": \"2004-Q3\",\n" +
                    "        \"_id\": 1\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.000543\",\n" +
                    "        \"quarter\": \"2004-Q4\",\n" +
                    "        \"_id\": 2\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.00062\",\n" +
                    "        \"quarter\": \"2005-Q1\",\n" +
                    "        \"_id\": 3\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.000634\",\n" +
                    "        \"quarter\": \"2005-Q2\",\n" +
                    "        \"_id\": 4\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"volume_of_mobile_data\": \"0.000718\",\n" +
                    "        \"quarter\": \"2005-Q3\",\n" +
                    "        \"_id\": 5\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"_links\": {\n" +
                    "      \"start\": \"/api/action/datastore_search?limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\",\n" +
                    "      \"next\": \"/api/action/datastore_search?offset=5&limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\"\n" +
                    "    },\n" +
                    "    \"limit\": 5,\n" +
                    "    \"total\": 59\n" +
                    "  }\n" +
                    "}"
        )

        mockWebServer.enqueue(successResponse)

        val response = apiService.getMobileVol(source, key).execute().body()
        assertEquals(
            response?.help.toString(),
            ("https://data.gov.sg/api/3/action/help_show?name=datastore_search"))
    }

    @Test
    fun testFailureApi() {
        // Assign
        val response = MockResponse().setResponseCode(404)
        mockWebServer.enqueue(response)

        // Act
        val product = apiService.getMobileVol(source, key).execute()

        assertEquals(product.isSuccessful, false)
    }

    @Test
    fun testFailureCondition() {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))
        val response = apiService.getMobileVol(source, key).execute().message()

        assertEquals(response, "Client Error")
    }

    @Test
    fun testServerFailureCondition() {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))
        val response = apiService.getMobileVol(source, key).execute().message()

        assertEquals(response, "Server Error")
    }

    @Test
    @Throws
    fun testNoNetworkError() {
        val mock = Mockito.mock(DataApi::class.java)
        val failure = IOException("Network Error")
        val networkError = Calls.failure<DataResponse>(failure)
        Mockito.`when`(mock?.getMobileVol("", "")).thenReturn(networkError)

        try {
           val response = mock?.getMobileVol("", "")?.execute()

        }catch (e : Exception) {
            assertEquals(e.message, "Network Error")

        }
    }
}