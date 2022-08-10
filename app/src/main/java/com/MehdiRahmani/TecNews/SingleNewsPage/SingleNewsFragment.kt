package com.MehdiRahmani.TecNews.SingleNewsPage

import android.content.res.Configuration
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.Model.News
import com.MehdiRahmani.TecNews.R
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.jsoup.Jsoup
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import kotlin.coroutines.Continuation

class SingleNewsFragment() : Fragment() {

    var article: Articles? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.single_news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cast(view)
    }

    private fun cast(view: View) {
        val newsIMG: ImageView = view.findViewById(R.id.img_news)
        val newsTitle: AppCompatTextView = view.findViewById(R.id.single_news_title)
        val newsText: AppCompatTextView = view.findViewById(R.id.main_news)

        val fab: ExtendedFloatingActionButton = view.findViewById(R.id.EFAB_single_news)


        val viewModel: NewsPageViewModel by viewModels()

        if (article != null) {
            viewModel.get_news().postValue(article)
        }

        viewModel.get_news().observe(viewLifecycleOwner, Observer<Articles> { data ->

            newsTitle.text = data!!.title
            fabOnClick(data!!, fab)
            fabHide(fab)
            Thread(object : Runnable {
                override fun run() {
                    setText(newsText, data!!.url)
                }
            }).start()

            showImage(data!!, newsIMG, this)
        })
    }

    private fun setText(newsText: AppCompatTextView, u: String) {

        val url = URL(u)
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            val text = urlConnection.inputStream.bufferedReader().readText()
            Log.d("UrlTest", text)
            newsText.post {
                newsText.text = Jsoup.parse(text).normalise().text()
            }
        } finally {
            urlConnection.disconnect()
        }

    }


    private fun fabHide(fab: ExtendedFloatingActionButton) {
//        TODO : if scroll down hide and up is show

    }


    private fun fabOnClick(data: Articles, fab: ExtendedFloatingActionButton) {
        fab.setOnClickListener {
//            TODO : go to web view (I can use intents)
        }
    }

    private fun showImage(data: Articles, imageView: ImageView, fragment: Fragment) {

        val orientation = resources.configuration.orientation
        if (data.urlToImage != null && orientation == Configuration.ORIENTATION_PORTRAIT) {
            Glide.with(fragment)
                .load(data.urlToImage)
                .into(imageView)
        }
    }


}