package com.MehdiRahmani.TecNews.NoInternetConn

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.MehdiRahmani.TecNews.Main.MainActivity
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.R

class NoInternetConnFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.internet_disconnected, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_retry).setOnClickListener {
            val conn =
                requireActivity().getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (mainViewModel!!.isInternetDisconnect(conn))
                mainViewModel!!.setHomeFragment()
        }
    }
}