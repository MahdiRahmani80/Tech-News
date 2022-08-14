package com.MehdiRahmani.TecNews.HomeViewPagerFragment

import android.content.Context.CONNECTIVITY_SERVICE
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MehdiRahmani.TecNews.Home.changeMoodButton
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.R
import com.google.android.material.snackbar.Snackbar

class BottomFragment(private var position: Int = 0, private var tab: String = "Google") :
    Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_tab_viewpager_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: BottomHomeViewModel by viewModels()
        val recyclerView: RecyclerView = view.findViewById(R.id.rec_company_news)

        ifConfigurationChanged(recyclerView)

        poorNetworkSnack(view)

        viewModel.getNews(position, tab)
            .observe(requireActivity(), Observer<List<Articles>> { newsList ->
                recyclerView.adapter = BottomAdapter(newsList)
            })

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun poorNetworkSnack(v: View) {
        val conn = requireActivity().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (!mainViewModel!!.isInternetDisconnect(conn)) {

            val snack: ConstraintLayout? = activity?.findViewById<ConstraintLayout>(R.id.main)
            val snackbar =
                Snackbar.make(snack!!, "Check your network connection", Snackbar.LENGTH_SHORT)

            snackbar.show()


        }
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

