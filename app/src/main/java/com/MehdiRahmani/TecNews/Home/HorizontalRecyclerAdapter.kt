package com.MehdiRahmani.TecNews.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.MehdiRahmani.TecNews.Home.HomeAdapter.ViewHolder
import com.MehdiRahmani.TecNews.Model.News
import com.MehdiRahmani.TecNews.R
import com.google.android.material.textview.MaterialTextView

class HomeAdapter(private val news: List<News>) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_top_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.news_title.text = news[position].title
        holder.news_disc.text = news[position].description
        holder.news_author.text = news[position].author
        holder.news_time.text = news[position].publishedAt

    }

    override fun getItemCount(): Int {
        return news.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val news_title: MaterialTextView = view.findViewById(R.id.news_title)
        val news_disc: MaterialTextView = view.findViewById(R.id.news_disc)
        val news_author: MaterialTextView = view.findViewById(R.id.mtv_author)
        val news_time: MaterialTextView = view.findViewById(R.id.mtv_publish_time)
    }
}