package com.MehdiRahmani.TecNews.HomeViewPagerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.MehdiRahmani.TecNews.R

class BottomFragment(private var position:Int = 0 ): Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_tab_viewpager_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rec_company_news)
        val tv: TextView = view.findViewById(R.id.text)
        tv.text = "TAB $position"
    }
}

