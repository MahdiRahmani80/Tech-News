package com.MehdiRahmani.TecNews.Main

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.MehdiRahmani.TecNews.Home.HomeFragment
import java.util.*
import kotlin.concurrent.schedule

class MainViewModel : ViewModel() {

    private var fragment_state:Fragment? = null
    private val fr: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>().also {
            loadFragment()
        }
    }



    fun makeFragment(): MutableLiveData<Fragment> {
        fr.postValue(fragment_state)
        return fr
    }

    private fun loadFragment() {

        fragment_state = SplashFragment()
        Timer().schedule(2000) {
            fragment_state = HomeFragment()
            fr.postValue(fragment_state)
        }
    }


}