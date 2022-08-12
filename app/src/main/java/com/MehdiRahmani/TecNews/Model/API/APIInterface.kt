package com.MehdiRahmani.TecNews.Model.API

import com.MehdiRahmani.TecNews.Model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("everything?sortBy=publishedAt&language=en")
    fun getCompanyNews(@Query("q") q:String ,@Query("apiKey") apiKey:String): Call<News>

    @GET("top-headlines?category=business&pageSize=35&country=us&language=en")
    fun getTOPNews(@Query("apiKey") apiKey:String): Call<News>

}