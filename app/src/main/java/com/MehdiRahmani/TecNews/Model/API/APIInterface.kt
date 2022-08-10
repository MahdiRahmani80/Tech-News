package com.MehdiRahmani.TecNews.Model.API

import com.MehdiRahmani.TecNews.Model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("everything?sortBy=publishedAt&apiKey=e0d30c3f9b244cd788e901dac36f28f3&language=en")
    fun getCompanyNews(@Query("q") q:String ): Call<News>

    @GET("top-headlines?category=business&pageSize=35&country=us&apiKey=e0d30c3f9b244cd788e901dac36f28f3&language=en")
    fun getTOPNews(): Call<News>

}