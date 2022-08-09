package com.MehdiRahmani.TecNews.HomeViewPagerFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.MehdiRahmani.TecNews.Model.News

class BottomHomeViewModel: ViewModel() {

    private var pos:Int? = null
    private var postNews:List<News>? =null
    private val news: MutableLiveData<List<News>> by lazy {
        MutableLiveData<List<News>>().also{
            getNewsFromDB(pos!!)
        }
    }

    fun getNews(position:Int): LiveData<List<News>> {
        pos=position
        news.postValue(postNews!!)
        return news
    }

    private fun getNewsFromDB(position:Int) {
//        TODO : GET NEWS

        val listNews = ArrayList<News>()

        val news = News()
        news.author="Jane Marsh"
        news.title="Reducing the Risk for Housefires with EV Charging"
        news.description="Once a fringe technology, electric vehicles (EVs) are now a common sight. As more people leave their gas-powered cars for these more eco-friendly options, some unexpected obstacles have surfaced. Some drivers worry that their EVs may catch fire while charging…"
        news.publishedAt="2022-08-02"
        news.urlToImage="https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Model_Y_far_side_airbag.jpg"

        listNews.add(news)
        listNews.add(news)
        listNews.add(news)
        listNews.add(news)
        listNews.add(news)
        listNews.add(news)
        listNews.add(news)
        listNews.add(news)
        listNews.add(news)
        postNews = listNews


    }
}