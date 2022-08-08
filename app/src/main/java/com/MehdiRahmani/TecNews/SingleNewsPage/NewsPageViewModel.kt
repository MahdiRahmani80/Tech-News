package com.MehdiRahmani.TecNews.SingleNewsPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.Model.News

class NewsPageViewModel : ViewModel() {

    private val news: MutableLiveData<News> by lazy {
        MutableLiveData<News>()
    }

     fun get_news(): MutableLiveData<News> {
        return news
    }

}
