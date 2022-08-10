package com.MehdiRahmani.TecNews.SingleNewsPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.Model.News

class NewsPageViewModel : ViewModel() {

    private val news: MutableLiveData<Articles> by lazy {
        MutableLiveData<Articles>()
    }

     fun get_news(): MutableLiveData<Articles> {
        return news
    }

}
