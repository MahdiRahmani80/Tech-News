package com.MehdiRahmani.TecNews.HomeViewPagerFragment

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

class BottomHomeViewModel: ViewModel() {

    private var tabName:String? =null
    private var pos:Int? = null
    private var postNews:List<Articles>? =null
    private val news: MutableLiveData<List<Articles>> by lazy {
        MutableLiveData<List<Articles>>().also{
            getNewsFromDB()
        }
    }

    fun getNews(position:Int,tab:String): LiveData<List<Articles>> {
        tabName=tab
        pos=position
//        news.postValue(postNews!!)
        return news
    }

    private fun getNewsFromDB() {
        if(tabName != null) {
            val service = APIClient().serviceAPI().getCompanyNews(tabName!!)

            service.enqueue(object : Callback<News>{
                override fun onResponse(call: Call<News>?, response: Response<News>?) {
                    if(response?.body() != null && response.isSuccessful){
                        Log.d("RETD","${response.body()}")
                        news.postValue(response.body().articles)
                    }
                }

                override fun onFailure(call: Call<News>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })
        }


    }
}