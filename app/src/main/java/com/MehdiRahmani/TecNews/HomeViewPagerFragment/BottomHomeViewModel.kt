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
        val n = News()
        n.title = "Title $pos"

        listNews.add(n)
        postNews = listNews


    }
}