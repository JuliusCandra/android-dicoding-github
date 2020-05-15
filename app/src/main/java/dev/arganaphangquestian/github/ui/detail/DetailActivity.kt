package dev.arganaphangquestian.github.ui.detail

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.data.entity.DetailUser
import dev.arganaphangquestian.github.data.entity.User
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel by viewModel<DetailViewModel>()
    private lateinit var username: String
    private val pagers = listOf("Follower", "Following")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        loadData()
    }

    private fun init() {
        setContentView(R.layout.activity_detail)
        username = this.intent.getStringExtra("username") ?: ""
    }

    @Suppress("unchecked_cast")
    private fun loadData() {
        detailViewModel.getDetails(username).observe(this, Observer {
            val detail = it["detail"] as DetailUser
            val follower = it["follower"]
            val following = it["following"]
            println(follower)
            println(following)

            vp_detail.apply {
                adapter = DetailAdapter(listOf(follower as List<User>, following as List<User>))
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
}