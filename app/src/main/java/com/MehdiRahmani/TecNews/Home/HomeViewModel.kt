package com.MehdiRahmani.TecNews.Home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.Model.API.APIClient
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.Model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var isLoginCreated = false
    private var news_state: News? = null
    private val topNews: MutableLiveData<List<Articles>> by lazy {
        MutableLiveData<List<Articles>>()
    }
    private var tab_data: List<String>? = null
    private val tab_title: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            tab_data = mkTabTitles()
        }
    }

    fun getTabs(): LiveData<List<String>> {
        tab_title.postValue(tab_data)
        return tab_title
    }

    private fun mkTabTitles(): ArrayList<String> {
        val tabList = ArrayList<String>()
        tabList.add("Apple")
        tabList.add("Microsoft")
        tabList.add("Google")
        tabList.add("RedHat")
        tabList.add("Yahoo")
        tabList.add("Meta")
        tabList.add("Samsung")
        tabList.add("Dell")
        return tabList
    }

    fun getTopNews(): LiveData<List<Articles>> {
        getTopNewsFromServer()
        return topNews

    }

    private fun getTopNewsFromServer() {

        val loadingNewsList: ArrayList<Articles> = ArrayList<Articles>()
        val loadingNews = Articles()
        loadingNewsList.add(loadingNews)
        loadingNewsList.add(loadingNews)
        loadingNewsList.add(loadingNews)
        loadingNewsList.add(loadingNews)
        if (!isLoginCreated) {
            isLoginCreated = true
            sendRequest()
            topNews.postValue(loadingNewsList)
        }

    }


    private fun sendRequest(){
        val service = APIClient().serviceAPI().getTOPNews(mainViewModel!!.get_api_key())
        try {
            service.enqueue(object : Callback<News> {

                override fun onResponse(call: Call<News>?, response: Response<News>?) {
                    if (response?.body() != null && response != null) {
                        news_state = response?.body()
                        topNews?.postValue(news_state!!.articles)
                        Log.e("Retrofit", response.toString())
                    }
                }

                override fun onFailure(call: Call<News>?, t: Throwable?) {
                    Log.e("RetrofitError", t.toString())
                }
            })

        } catch (e: Exception) {
            Log.e("RetrofitCatch", e.toString())
        }
    }

}