package com.MehdiRahmani.TecNews.SingleNewsPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.MehdiRahmani.TecNews.Model.News

class NewsPageViewModel: ViewModel() {

    private var news_data:News? = null
    private val news:MutableLiveData<News> by lazy{
        MutableLiveData<News>().also(){
            getNewsFromModel()
        }
    }

    fun getNews(): LiveData<News> {
        news.postValue(news_data)
        return news
    }

    private fun getNewsFromModel(){
//        TODO : get news form api
//        TODO : set data in news post
    }
}