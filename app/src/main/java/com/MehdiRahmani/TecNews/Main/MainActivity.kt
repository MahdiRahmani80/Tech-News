package com.MehdiRahmani.TecNews.Main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.MehdiRahmani.TecNews.R
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm:MainViewModel by viewModels()
        vm.makeFragment().observe(this, Observer<Fragment> { fragment -> updateFragment(fragment) })

    }


    private fun updateFragment(fr: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fr).commit()
    }
}