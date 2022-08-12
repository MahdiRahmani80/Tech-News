package com.MehdiRahmani.TecNews.Home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.MehdiRahmani.TecNews.HomeViewPagerFragment.BottomFragment

class ViewPagerAdapter(frActivity: FragmentActivity, private var tabList: List<String>) :
    FragmentStateAdapter(frActivity) {

    override fun getItemCount(): Int = tabList.size
    override fun createFragment(position: Int): Fragment = BottomFragment(position!!,tabList[position])
}