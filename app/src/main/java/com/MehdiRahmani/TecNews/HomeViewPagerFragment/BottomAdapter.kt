package com.MehdiRahmani.TecNews.HomeViewPagerFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.MehdiRahmani.TecNews.HomeViewPagerFragment.BottomAdapter.*
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.Model.News
import com.MehdiRahmani.TecNews.R
import com.google.android.material.textview.MaterialTextView

class BottomAdapter(private val newsList:List<News>):
    RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_company_news,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.news_title.text = newsList[position].title
        holder.news_disc.text = newsList[position].description
        holder.news_author.text = newsList[position].author
        holder.news_time.text = newsList[position].publishedAt

        holder.view.setOnClickListener(View.OnClickListener {
            mainViewModel!!.setNews(newsList[position])
        })
    }

    override fun getItemCount(): Int {
        return newsList.size
    }



    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val news_title: MaterialTextView = view.findViewById(R.id.news_title)
        val news_disc: MaterialTextView = view.findViewById(R.id.news_disc)
        val news_author: MaterialTextView = view.findViewById(R.id.mtv_author)
        val news_time: MaterialTextView = view.findViewById(R.id.mtv_publish_time)
    }
}