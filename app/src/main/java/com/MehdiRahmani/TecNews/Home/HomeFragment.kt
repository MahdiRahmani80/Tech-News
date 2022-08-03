package com.MehdiRahmani.TecNews.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.MehdiRahmani.TecNews.Model.News
import com.MehdiRahmani.TecNews.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saved_savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel:HomeViewModel by viewModels()
        mkHorizontalRecycler(view,viewModel)

    }

    private fun mkHorizontalRecycler(view:View,viewModel:HomeViewModel){
        val recycler: RecyclerView = view.findViewById(R.id.REC_top_news)
        val SGLManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recycler.layoutManager = SGLManager

        viewModel.getTopNews().observe(viewLifecycleOwner, Observer<List<News>>{
                news -> recycler.adapter=HomeAdapter(news)
        })
    }


}



