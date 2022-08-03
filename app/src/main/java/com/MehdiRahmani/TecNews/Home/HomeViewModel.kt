package com.MehdiRahmani.TecNews.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.MehdiRahmani.TecNews.Model.News

class HomeViewModel: ViewModel() {

    private var news_state:List<News>? = null
    private val topNews:MutableLiveData<List<News>> by lazy{
        MutableLiveData<List<News>>().also {
            getTopNewsFromServer()
        }
    }

    fun getTopNews(): LiveData<List<News>> {
        topNews.postValue(news_state)
        return topNews
    }

    private fun getTopNewsFromServer() {
//        Get News From Retrofit, and make fun in models
        val newsList = ArrayList<News>()

        val news = News()
        news.author="Mike Smitka"
        news.title="China NEV Segment Analysis: BYD, VW, And Geely Stand Out"
        news.description="China's auto industry faces numerous short-run and long-run headwinds; NEVs will not be exempt. it is foolish to project recent growth beyond the next year."
        news.publishedAt="2022-08-02"


        val news2 = News()
        news2.author="Jane Marsh"
        news2.title="Reducing the Risk for Housefires with EV Charging"
        news2.description="Once a fringe technology, electric vehicles (EVs) are now a common sight. As more people leave their gas-powered cars for these more eco-friendly options, some unexpected obstacles have surfaced. Some drivers worry that their EVs may catch fire while charging…"
        news2.publishedAt="2022-08-02"

        newsList.add(news)
        newsList.add(news2)
        newsList.add(news)
        newsList.add(news2)
        newsList.add(news)


        news_state = newsList
    }
}