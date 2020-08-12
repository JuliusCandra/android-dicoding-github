package dev.arganaphangquestian.github.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.data.entity.User
import dev.arganaphangquestian.github.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favourite.*

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavouriteFragment()
    }

    private lateinit var favouriteViewModel: FavouriteViewModel
    private val favouriteAdapter = FavouriteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favouriteViewModel.favourites.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                favouriteAdapter.setData(it)
                rv_favourite.apply {
                    adapter = favouriteAdapter
                    layoutManager = LinearLayoutManager(this@FavouriteFragment.context)
                }
            }
        })

        favouriteAdapter.setOnClickCallback(object : FavouriteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val intent = Intent(this@FavouriteFragment.context, DetailActivity::class.java)
                intent.putExtra("username", data.login)
                this@FavouriteFragment.activity?.startActivity(intent)
            }
        })
    }

}