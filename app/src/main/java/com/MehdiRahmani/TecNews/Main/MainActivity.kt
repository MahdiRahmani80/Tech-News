package com.MehdiRahmani.TecNews.Main

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.MehdiRahmani.TecNews.R

var mainViewModel: MainViewModel? = null
class MainActivity : AppCompatActivity() {

    private var addFR: MutableLiveData<Fragment>? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (mainViewModel == null) {
            mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        }

        val conn = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        mainViewModel!!.makeFragment(conn)
            .observe(this, Observer<Fragment> { fragment -> updateFragment(fragment) })


        addFR = mainViewModel!!.addFragment()
        addFR!!.observe(this, Observer<Fragment> { fragment ->
            if (fragment != null) {
                addFragment(fragment)
//                addFR.postValue(null)
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

    override fun onBackPressed() {

        if(addFR !== null) addFR!!.postValue(null)
        super.onBackPressed()
    }
}