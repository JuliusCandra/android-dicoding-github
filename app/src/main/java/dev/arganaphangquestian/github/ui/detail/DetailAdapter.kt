package dev.arganaphangquestian.github.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.data.entity.User
import kotlinx.android.synthetic.main.card_activity_detail.view.*

class DetailAdapter(val list: List<List<User>>) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.card_activity_detail, container, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var adapter: UserAdapter
        fun bind(data: List<User>) {
            adapter = UserAdapter(data)

            adapter.setOnClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("username", data.login)
                    itemView.context.startActivity(
                        intent
                    )
                }

            })

            itemView.rv_detail.apply {
                layoutManager = LinearLayoutManager(itemView.context)
                this.adapter = this@ViewHolder.adapter
            }
        }
    }

}