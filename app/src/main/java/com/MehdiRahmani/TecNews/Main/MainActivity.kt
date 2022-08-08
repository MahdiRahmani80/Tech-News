package com.MehdiRahmani.TecNews.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.MehdiRahmani.TecNews.R

var mainViewModel: MainViewModel? = null


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (mainViewModel == null) {
            mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        }


        mainViewModel!!.makeFragment()
            .observe(this, Observer<Fragment> { fragment -> updateFragment(fragment) })


        val addFR: MutableLiveData<Fragment>? = mainViewModel!!.addFragment()
        addFR!!.observe(this, Observer<Fragment> { fragment ->
            if (fragment != null) {
                addFragment(fragment)
                addFR.postValue(null)
            }
        })


    }


    private fun updateFragment(fr: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.change_fr_1, R.anim.change_fr_2)
            .replace(R.id.frame_layout, fr)
            .commit()
    }

    private fun addFragment(fr: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, fr)
            .addToBackStack("single_news")
            .commit()


    }

}