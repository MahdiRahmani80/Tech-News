package com.MehdiRahmani.TecNews.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.MehdiRahmani.TecNews.R

class SplashFragment: Fragment() {

    override fun onCreateView (inflater: LayoutInflater,container: ViewGroup?,saved_savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState:Bundle?){
        super.onViewCreated(view,savedInstanceState)
    }
}