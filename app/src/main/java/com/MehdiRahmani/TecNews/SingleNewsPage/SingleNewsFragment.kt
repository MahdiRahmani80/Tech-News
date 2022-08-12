package com.MehdiRahmani.TecNews.SingleNewsPage

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.R
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.jsoup.Jsoup
import java.net.HttpURLConnection
import java.net.URL

class SingleNewsFragment : Fragment() {

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

        viewModel.get_news().observe(viewLifecycleOwner) { data ->

            newsTitle.text = data!!.title
            fabOnClick(data, fab)
            Thread { setText(newsText, data.url) }.start()

            showImage(data, newsIMG, this)
        }
    }

    private fun setText(newsText: AppCompatTextView, u: String) {

        val url = URL(u)
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.usingProxy()
        var text = "News details not found , you can click on 'GO TO WEBSITE'"


        try {
            text = urlConnection.inputStream.bufferedReader().readText()
            if (text.isNotEmpty())
                text = Jsoup.parse(text).normalise().text()
        } finally {
            urlConnection.disconnect()
        }


        newsText.post {
            newsText.text = text
        }
    }


    private fun fabOnClick(data: Articles, fab: ExtendedFloatingActionButton) {
        fab.setOnClickListener {

            //  GO TO WEB BROWSER
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(data.url)
            startActivity(openUrl)
        }
    }

    private fun showImage(data: Articles, imageView: ImageView, fragment: Fragment) {

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            val circularProgressDrawable = CircularProgressDrawable(requireContext())
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(fragment)
                .load(data.urlToImage)
                .placeholder(circularProgressDrawable)
                .override(MATCH_PARENT, WRAP_CONTENT)
                .into(imageView)

        }
    }


}