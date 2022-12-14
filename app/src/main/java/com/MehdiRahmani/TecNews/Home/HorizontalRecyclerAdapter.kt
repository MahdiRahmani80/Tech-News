package com.MehdiRahmani.TecNews.Home

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.MehdiRahmani.TecNews.Home.HorizontalRecyclerAdapter.ViewHolder
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.Model.News
import com.MehdiRahmani.TecNews.R
import com.google.android.material.textview.MaterialTextView
import org.jsoup.Jsoup

class HorizontalRecyclerAdapter(private val newsList: List<Articles>) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_top_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (newsList[position].title != ""  ) {
            showNews(holder, position)
            holder.rel_loading.visibility = GONE
            holder.rel_news.visibility = VISIBLE
        }
        else {
            holder.rel_loading.visibility = VISIBLE
            holder.rel_news.visibility = GONE
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showNews(holder: ViewHolder, position: Int) {

        var disc = newsList[position].description
        if (disc != null) {
            disc= Jsoup.parse(newsList[position].description.replace("\n", " "))
                .normalise().text()
        }
        else disc="Description not found !"

        holder.news_title.text = newsList[position].title
        holder.news_disc.text = disc

        if (newsList[position].author!=null)
            holder.news_author.text = newsList[position].author
        else holder.news_author.text = "Author Not Found"

        holder.news_time.text = newsList[position].publishedAt.substring(0,10)

        holder.view.setOnClickListener(View.OnClickListener {
            mainViewModel!!.setNews(newsList[position])
        })
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val news_title: MaterialTextView = view.findViewById(R.id.news_title)
        val news_disc: MaterialTextView = view.findViewById(R.id.news_disc)
        val news_author: MaterialTextView = view.findViewById(R.id.mtv_author)
        val news_time: MaterialTextView = view.findViewById(R.id.mtv_publish_time)
        val rel_news : RelativeLayout = view.findViewById(R.id.main)
        val rel_loading : RelativeLayout = view.findViewById(R.id.loading)
    }


}