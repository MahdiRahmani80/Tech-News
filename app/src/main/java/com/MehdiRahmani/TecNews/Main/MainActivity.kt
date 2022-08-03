package com.MehdiRahmani.TecNews.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.MehdiRahmani.TecNews.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm:MainViewModel by viewModels()
        vm.makeFragment().observe(this, Observer<Fragment> { fragment -> updateFragment(fragment) })

    }


    private fun updateFragment(fr: Fragment){
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.change_fr_1,R.anim.change_fr_2)
            .replace(R.id.frame_layout,fr)
            .commit()
    }
}