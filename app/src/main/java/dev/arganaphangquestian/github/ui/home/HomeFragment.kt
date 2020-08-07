package dev.arganaphangquestian.github.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.data.entity.User
import dev.arganaphangquestian.github.databinding.FragmentHomeBinding
import dev.arganaphangquestian.github.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding
    private val homeAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeBinding.viewModel = homeViewModel
        homeBinding.lifecycleOwner = this
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchHandler()
        loadData()
    }

    private fun searchHandler() {
        var searchFor = ""
        homeViewModel.searchText.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                rv_main.adapter = null
            }
            val searchText = it.trim()
            if (searchText == searchFor)
                return@Observer
            searchFor = searchText
            CoroutineScope(Dispatchers.IO).launch {
                delay(300L)
                if (searchFor != searchText)
                    return@launch
                if (searchFor != "") {
                    homeViewModel.refereshSearch(searchText)
                }
            }
        })
    }

    private fun loadData() {
        homeAdapter.setOnClickCallback(object : HomeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val intent = Intent(this@HomeFragment.context, DetailActivity::class.java)
                intent.putExtra("username", data.login)
                this@HomeFragment.activity?.startActivity(intent)
            }

        })
        rv_main.layoutManager = LinearLayoutManager(this@HomeFragment.context)
        homeViewModel.users.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                homeAdapter.setData(it)
                rv_main.apply {
                    adapter = homeAdapter
                }
            }
        })
    }
}