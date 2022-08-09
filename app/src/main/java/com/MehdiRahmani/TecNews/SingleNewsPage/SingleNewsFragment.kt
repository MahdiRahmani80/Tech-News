package com.MehdiRahmani.TecNews.SingleNewsPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.MehdiRahmani.TecNews.Model.News
import com.MehdiRahmani.TecNews.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class SingleNewsFragment():Fragment() {

    var news:News?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.single_news_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cast(view)
    }

    private fun cast(view:View){
        val newsIMG: ImageView = view.findViewById(R.id.img_news)
        val newsTitle: AppCompatTextView = view.findViewById(R.id.single_news_title)
        val newsText: AppCompatTextView = view.findViewById(R.id.main_news)
        val fab:ExtendedFloatingActionButton = view.findViewById(R.id.EFAB_single_news)

       val viewModel:NewsPageViewModel by viewModels()

        if (news != null){
            viewModel.get_news().postValue(news)
        }

        viewModel.get_news().observe(viewLifecycleOwner, Observer<News>{ data->

            newsTitle.text = data!!.title
            newsText.text = data!!.description
            fabOnClick(data!!,fab)
            fabHide(fab)
            showImage(data!!,newsIMG)
        })
    }


    private fun fabHide(fab: ExtendedFloatingActionButton) {
//        TODO : if scroll down hide and up is show

    }


    private fun fabOnClick(data:News,fab:ExtendedFloatingActionButton){
        fab.setOnClickListener {
//            TODO : go to web view (I can use intents)
        }
    }

    private fun showImage(data:News,imageView:ImageView){
//        TODO : Add GLIDE and set Image
    }

}