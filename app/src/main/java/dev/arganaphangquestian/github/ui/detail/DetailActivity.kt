package dev.arganaphangquestian.github.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.data.entity.DetailUser
import dev.arganaphangquestian.github.data.entity.User
import dev.arganaphangquestian.github.databinding.ActivityDetailBinding
import dev.arganaphangquestian.github.utils.NetworkState
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var username: String
    private val pagers = listOf("Follower", "Following")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        loadData()
    }

    private fun init() {
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        username = this.intent.getStringExtra("username") ?: ""
        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        detailBinding.apply {
            lifecycleOwner = this@DetailActivity
            viewModel = detailViewModel
        }
    }

    @Suppress("unchecked_cast")
    private fun loadData() {
        detailViewModel.usernameGithub.value = username
        detailViewModel.findFavouriteByUsername()
        detailViewModel.getDetails(username).observe(this, Observer { net ->
            if (net != null && net.status == NetworkState.Status.SUCCESS){
                detailViewModel.details.observe(this, Observer {
                    val detail = it["detail"] as DetailUser
                    val follower = it["follower"] as List<User>
                    val following = it["following"] as List<User>

                    vp_detail.apply {
                        adapter =
                            DetailAdapter(listOf(follower, following))
                    }
                    TabLayoutMediator(tl_detail, vp_detail) { tab, position ->
                        tab.text =
                            if (pagers[position].length > 20) pagers[position].substring(
                                0,
                                20 - 1
                            ) + "..." else pagers[position]
                    }.attach()

                    Glide.with(this).load(Uri.parse(detail.avatarUrl)).centerCrop().into(iv_detail)
                    tv_name.text = detail.name
                    tv_username.text = detail.login
                })
            }
        })
    }
}