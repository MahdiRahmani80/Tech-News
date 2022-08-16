package com.MehdiRahmani.TecNews.SearchFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.R

class SearchFragment: Fragment() {

    var newsList:List<Articles>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.seach_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rec_search_news)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = SearchAdapter(newsList!!)
    }

}