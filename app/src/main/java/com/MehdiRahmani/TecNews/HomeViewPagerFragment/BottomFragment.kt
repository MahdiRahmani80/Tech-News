package com.MehdiRahmani.TecNews.HomeViewPagerFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.MehdiRahmani.TecNews.Model.News
import com.MehdiRahmani.TecNews.R

class BottomFragment(private var position: Int = 0) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_tab_viewpager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel:BottomHomeViewModel by viewModels()
        val recyclerView: RecyclerView = view.findViewById(R.id.rec_company_news)
        val tv: TextView = view.findViewById(R.id.text)

        viewModel.getNews(position).observe(requireActivity(), Observer<List<News>> { newsList->
            for (i in newsList)
            tv.text = "TAB ${i.title}"
        })

    }



//    CHECK ORIENTATION

    /*
     val orientation= getResources().configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){

        } else{

        }
     */

}

