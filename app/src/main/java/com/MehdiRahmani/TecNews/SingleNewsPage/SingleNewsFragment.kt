package com.MehdiRahmani.TecNews.SingleNewsPage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.R
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.net.HttpURLConnection
import java.net.URL

class SingleNewsFragment : Fragment() {

    var article: Articles? = null
    private var newsTitle: AppCompatTextView? = null
    private var newsText: AppCompatTextView? = null
    private var fab: ExtendedFloatingActionButton? = null
    private var newsIMG: ImageView? = null
    private var conn: ConnectivityManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.single_news_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cast(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun cast(view: View) {
        newsIMG = view.findViewById(R.id.img_news)
        newsTitle = view.findViewById(R.id.single_news_title)
        newsText = view.findViewById(R.id.main_news)
        fab = view.findViewById(R.id.EFAB_single_news)


        val viewModel: NewsPageViewModel by viewModels()

        if (article != null) {
            viewModel.get_news().postValue(article)
        }

        showContent(viewModel)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showContent(viewModel: NewsPageViewModel) {

        viewModel.get_news().observe(viewLifecycleOwner) { data ->

            showImage(data, newsIMG!!, this)
            newsTitle!!.text = data!!.title
            fabOnClick(data, fab!!, viewModel)
            Thread {
                val conn =
                    requireActivity().getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
                if (mainViewModel!!.isInternetDisconnect(conn))
                    lifecycleScope.launchWhenCreated {
                        withContext(Dispatchers.IO) {
                            setText(newsText!!, data.url)
                        }
                    }
                else if (fab != null) {
                    newsText!!.post {
                        poorNetworkSnack(viewModel)
                        fab!!.text = "Retry"
                        fab!!.gravity = Gravity.CENTER
                    }
                }
            }.start()
        }
    }

    private fun setText(newsText: AppCompatTextView, u: String) {

        val url = URL(u)
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.usingProxy()
        var text: String


        try {
            text = urlConnection.inputStream.bufferedReader().readText().replace("<[^>]*>"," ")
            if (text.isNotEmpty())
                text = Jsoup.parse(text).text()
        } finally {
            urlConnection.disconnect()
        }
        newsText.post {
            newsText.text = text
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun fabOnClick(
        data: Articles,
        fab: ExtendedFloatingActionButton,
        viewModel: NewsPageViewModel
    ) {
        fab.setOnClickListener {

            if (!mainViewModel!!.isInternetDisconnect(conn!!)) {
                //  GO TO WEB BROWSER
                val openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse(data.url)
                startActivity(openUrl)
            } else {
                showContent(viewModel)
            }
            fab.text = "Go To Website"
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun poorNetworkSnack(viewModel: NewsPageViewModel) {
        conn =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (!mainViewModel!!.isInternetDisconnect(conn!!)) {

            val snack: ConstraintLayout? = activity?.findViewById(R.id.main)
            val snackBar =
                Snackbar.make(snack!!, "Check your network connection", Snackbar.LENGTH_SHORT)
            snackBar.setAction("Retry") {
                showContent(viewModel)
            }
            snackBar.show()


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