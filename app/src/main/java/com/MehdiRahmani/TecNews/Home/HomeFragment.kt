package com.MehdiRahmani.TecNews.Home

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.Model.News
import com.MehdiRahmani.TecNews.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

var changeMoodButton:ExtendedFloatingActionButton? = null
class HomeFragment : Fragment() {

    private var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saved_savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: HomeViewModel by viewModels()
        mkHorizontalRecycler(view, viewModel)
        mkTabLayout(view.context, view, viewModel)
        setTheme(view)
//        TODO => add search

    }

    private fun setTheme(v: View) {
        changeMoodButton = v.findViewById(R.id.EFAB_dark)
        val isNight = Configuration.UI_MODE_NIGHT_MASK + v.context.resources.configuration.uiMode

        when (isNight) {
            65 -> {
                changeMoodButton!!.setIconResource(R.drawable.ic_baseline_nights_stay_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            81 -> {
                changeMoodButton!!.setIconResource(R.drawable.ic_baseline_wb_sunny_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }


        changeMoodButton!!.setOnClickListener {
            val isNight2 = Configuration.UI_MODE_NIGHT_MASK + v.context.resources.configuration.uiMode
            when (isNight2) {
                81 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                65 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                else -> {
                    changeMoodButton!!.setIconResource(R.drawable.ic_baseline_nights_stay_24)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun mkHorizontalRecycler(view: View, viewModel: HomeViewModel) {
        val recycler: RecyclerView = view.findViewById(R.id.REC_top_news)
        val SGLManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recycler.layoutManager = SGLManager

        viewModel.getTopNews().observe(viewLifecycleOwner, Observer<List<Articles>> { news ->
            recycler.adapter = HorizontalRecyclerAdapter(news)
        })
    }

    private fun mkTabLayout(context: Context, view: View, viewModel: HomeViewModel) {
        tabLayout = view.findViewById(R.id.tl_home)
        val viewPager: ViewPager2 = view.findViewById(R.id.vp_home)

        viewModel.getTabs().observe(viewLifecycleOwner, Observer<List<String>> { data ->

//            SET ADAPTER
            viewPager.adapter = ViewPagerAdapter(requireActivity(), data)

            TabLayoutMediator(tabLayout!!, viewPager) { tab, position ->
                tab.text = data[position]
            }.attach()

        })

    }

}

