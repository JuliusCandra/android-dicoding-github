package dev.arganaphangquestian.github.ui.favourite

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
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
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
    }

}