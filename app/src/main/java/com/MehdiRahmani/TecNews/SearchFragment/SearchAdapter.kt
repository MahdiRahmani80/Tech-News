package com.MehdiRahmani.TecNews.SearchFragment

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.R
import com.google.android.material.textview.MaterialTextView
import org.jsoup.Jsoup

class SearchAdapter(val news:List<Articles>): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_company_news,parent, false)
        return SearchAdapter.ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (news[position].title != ""  ) {
            showNews(holder, position)
            holder.rel_loading.visibility = View.GONE
            holder.rel_news.visibility = View.VISIBLE
        }
        else {
            holder.rel_loading.visibility = View.VISIBLE
            holder.rel_news.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showNews(holder: ViewHolder, position: Int) {

        var disc = news[position].description
        if (disc != null) {
            disc= Jsoup.parse(news[position].description.replace("\n", " "))
                .normalise().text()
        }
        else disc="Description not found !"

        holder.news_title.text = news[position].title
        holder.news_disc.text = disc

        if (news[position].author!=null)
            holder.news_author.text = news[position].author
        else holder.news_author.text = "Author Not Found"

        holder.news_time.text = news[position].publishedAt.substring(0,10)

        holder.view.setOnClickListener(View.OnClickListener {
            mainViewModel!!.setNews(news[position])
        })
    }

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {

        val news_title: MaterialTextView = view.findViewById(R.id.news_title)
        val news_disc: MaterialTextView = view.findViewById(R.id.news_disc)
        val news_author: MaterialTextView = view.findViewById(R.id.mtv_author)
        val news_time: MaterialTextView = view.findViewById(R.id.mtv_publish_time)
        val rel_loading: RelativeLayout = view.findViewById(R.id.loading)
        val rel_news: RelativeLayout = view.findViewById(R.id.main_news)
    }

}