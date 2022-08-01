package com.MehdiRahmani.TecNews.Main

import androidx.fragment.app.Fragment
import com.MehdiRahmani.TecNews.Home.HomeFragment
import java.util.*
import kotlin.concurrent.schedule

class MainViewModel(main:MainActivity) {

    var main:MainActivity? = null
    init {
        this.main=main
    }

    fun makeFragment(){
        main?.updateFragment(splash())

        Timer().schedule(2000){
            main?.updateFragment(home())
        }
    }

    fun splash(): SplashFragment {
        return SplashFragment()
    }

    fun home(): HomeFragment {
        return HomeFragment()
    }

}