package com.MehdiRahmani.TecNews.Home

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager2.widget.ViewPager2
import com.MehdiRahmani.TecNews.Main.mainViewModel
import com.MehdiRahmani.TecNews.Model.Articles
import com.MehdiRahmani.TecNews.Model.News
import com.MehdiRahmani.TecNews.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

var changeMoodButton: ExtendedFloatingActionButton? = null

class HomeFragment : Fragment() {

    private var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saved_savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: HomeViewModel by viewModels()
        mkHorizontalRecycler(view, viewModel)
        mkTabLayout(view.context, view, viewModel)
        setTheme(view)
        search(view, viewModel)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun search(view: View, vm: HomeViewModel) {
        val search: TextInputEditText = view.findViewById(R.id.et_search)

        search.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                hideSoftKeyboard()
                vm.search(search.text.toString())
                    .observe(requireActivity(), Observer<List<Articles>> { data ->

                        if (data != null)
                            mainViewModel!!.setSearchNews(data)
                    })
                return@OnEditorActionListener true
            }
            false
        })
        vm.search(search.text.toString())
    }

    private fun setTheme(v: View) {
        changeMoodButton = v.findViewById(R.id.EFAB_dark)
        val isNight = Configuration.UI_MODE_NIGHT_MASK + v.context.resources.configuration.uiMode

        when (isNight) {
            65 -> {
                changeMoodButton!!.setIconResource(R.drawable.ic_baseline_nights_stay_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            81 -> {
                changeMoodButton!!.setIconResource(R.drawable.ic_baseline_wb_sunny_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }


        changeMoodButton!!.setOnClickListener {
            val isNight2 =
                Configuration.UI_MODE_NIGHT_MASK + v.context.resources.configuration.uiMode
            when (isNight2) {
                81 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                65 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                else -> {
                    changeMoodButton!!.setIconResource(R.drawable.ic_baseline_nights_stay_24)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun mkHorizontalRecycler(view: View, viewModel: HomeViewModel) {
        val recycler: RecyclerView = view.findViewById(R.id.REC_top_news)
        val SGLManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recycler.layoutManager = SGLManager

        viewModel.getTopNews().observe(viewLifecycleOwner, Observer<List<Articles>> { news ->
            recycler.adapter = HorizontalRecyclerAdapter(news)
        })
    }

    private fun mkTabLayout(context: Context, view: View, viewModel: HomeViewModel) {
        tabLayout = view.findViewById(R.id.tl_home)
        val viewPager: ViewPager2 = view.findViewById(R.id.vp_home)

        viewModel.getTabs().observe(viewLifecycleOwner, Observer<List<String>> { data ->

//            SET ADAPTER
            if (this != null) {
                viewPager.adapter = ViewPagerAdapter(requireActivity(), data)

                TabLayoutMediator(tabLayout!!, viewPager) { tab, position ->
                    tab.text = data[position]
                }.attach()
            }

        })

    }

    fun hideSoftKeyboard(){
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

