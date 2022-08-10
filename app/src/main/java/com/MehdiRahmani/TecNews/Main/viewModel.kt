package com.MehdiRahmani.TecNews.Main

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.MehdiRahmani.TecNews.Home.HomeFragment
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.SingleNewsPage.SingleNewsFragment
import java.util.*
import kotlin.concurrent.schedule

class MainViewModel : ViewModel() {

    private val singleNews = SingleNewsFragment()
    private var fragment_state: Fragment? = null
    private val fr: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>().also {
            loadFragment()
        }
    }

    private val go_news_single: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>()
    }

    fun makeFragment(): MutableLiveData<Fragment> = fr

    fun addFragment(): MutableLiveData<Fragment>? =go_news_single

    fun setNews(article: Articles) {

        singleNews.article = article
        go_news_single.postValue(singleNews)
    }

    private fun loadFragment() {


        Thread{
            fragment_state = SplashFragment()
            fr.postValue(fragment_state)
        }.start()

        Timer().schedule(2000) {
            fragment_state = HomeFragment()
            fr.postValue(fragment_state)
        }
    }


}