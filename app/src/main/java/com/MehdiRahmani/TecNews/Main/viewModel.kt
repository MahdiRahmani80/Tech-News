package com.MehdiRahmani.TecNews.Main

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.MehdiRahmani.TecNews.Home.HomeFragment
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.NoInternetConn.NoInternetConnFragment
import com.MehdiRahmani.TecNews.SingleNewsPage.SingleNewsFragment
import java.util.*
import kotlin.concurrent.schedule

@RequiresApi(Build.VERSION_CODES.M)
class MainViewModel : ViewModel() {

    private var conn: ConnectivityManager? = null
    private val singleNews = SingleNewsFragment()
    private val fr: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>().also {
            loadFragment()
        }
    }

    private val go_news_single: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun makeFragment(conn: ConnectivityManager): MutableLiveData<Fragment> {
        this.conn = conn
        return fr
    }

    fun setHomeFragment(){
        fr.postValue(HomeFragment())
    }

    fun addFragment(): MutableLiveData<Fragment> = go_news_single

    fun setNews(article: Articles) {
        singleNews.article = article
        go_news_single.postValue(singleNews)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadFragment() {

        Thread {
            fr.postValue(SplashFragment())
        }.start()

        Timer().schedule(2000) {
            if (isInternetDisconnect(conn!!)) {
                fr.postValue(HomeFragment())
            } else {
                fr.postValue(NoInternetConnFragment())
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isInternetDisconnect(conn: ConnectivityManager): Boolean {
        if (conn != null) {
            val capabilities = conn.getNetworkCapabilities(conn.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                    return true
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                    return true
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                    return true
            }
        }
        return false
    }

    fun get_api_key(): String {
        val keyList = ArrayList<String>()
        keyList.add("3617530aed3c4534a9587f9f539a60ef")
        keyList.add("e0d30c3f9b244cd788e901dac36f28f3")
        keyList.add("c32f1214ddf94bbeb6a2a170c6d51819")
        keyList.add("2dba03e2d18042c9aee259a0f6cb1b6e")
        keyList.add("c97b48b2b42f415da35eef5c3cb1c161")
        keyList.add("378765dbd377412a9e1adc680c9c3b7f")
        keyList.add("f3d555f3e8e643549cbfdb4e45e1fe1f")


        return keyList[(0 until keyList.size - 1).random()]
    }

}