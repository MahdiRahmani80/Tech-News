package com.MehdiRahmani.TecNews.HomeViewPagerFragment

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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

class BottomHomeViewModel: ViewModel() {


    private var isLoginCreated = false
    private var tabName:String? =null
    private var pos:Int? = null
    private var postNews:List<Articles>? =null
    private val news: MutableLiveData<List<Articles>> by lazy {
        MutableLiveData<List<Articles>>()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getNews(position:Int, tab:String): LiveData<List<Articles>> {
        tabName=tab
        pos=position

        val loadingNewsList: ArrayList<Articles> = ArrayList<Articles>()
        val loadingNews = Articles()
        loadingNewsList.add(loadingNews)
        loadingNewsList.add(loadingNews)
        loadingNewsList.add(loadingNews)
        loadingNewsList.add(loadingNews)
        loadingNewsList.add(loadingNews)
        loadingNewsList.add(loadingNews)
        if (!isLoginCreated) {
            isLoginCreated = true
            getNewsFromDB()
             news.postValue(loadingNewsList)
        }

        return news
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getNewsFromDB() {
        if(tabName != null) {
            val service = APIClient().serviceAPI().getCompanyNews(tabName!!,mainViewModel!!.get_api_key())

            service.enqueue(object : Callback<News>{
                override fun onResponse(call: Call<News>?, response: Response<News>?) {
                    if(response?.body() != null && response.isSuccessful){
                        news.postValue(response.body().articles)
                    }
                }

                override fun onFailure(call: Call<News>?, t: Throwable?) {
                    getNews(pos!!,tabName!!)
                }
            })
        }


    }
}