package com.MehdiRahmani.TecNews.Model.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    private val retrofit: Retrofit =Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun serviceAPI():APIInterface{
        return retrofit.create(APIInterface::class.java)
    }

}