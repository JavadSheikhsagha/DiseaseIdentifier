package com.a2mp.diseaseidentifier.Api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object ApiClient {

    fun getRetrofit(): ApiService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val list = listOf(
            "2b106pU6cNDPagMxUzrMbMGl",
            "2b10Dv6vk1WRSwa9a1Tphfg5Ze",
            "2b10bONfayDzFkcL24CYw1Pu",
            "2b10fxZUCvXw0Yxx2MlRTimdu"
        )

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://my-api.plantnet.org/v2/identify/all?api-key=${list.random()}")
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}