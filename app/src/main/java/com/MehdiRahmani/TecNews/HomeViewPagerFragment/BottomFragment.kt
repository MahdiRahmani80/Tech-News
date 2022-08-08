package com.MehdiRahmani.TecNews.HomeViewPagerFragment

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

        val viewModel: BottomHomeViewModel by viewModels()
        val recyclerView: RecyclerView = view.findViewById(R.id.rec_company_news)

        ifConfigurationChanged(recyclerView)

        viewModel.getNews(position).observe(requireActivity(), Observer<List<News>> { newsList ->
            recyclerView.adapter = BottomAdapter(newsList)
        })

    }

    private fun ifConfigurationChanged(recyclerView: RecyclerView) {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        }
    }

}

