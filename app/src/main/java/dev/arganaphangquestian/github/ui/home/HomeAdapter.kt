package dev.arganaphangquestian.github.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.data.entity.User
import kotlinx.android.synthetic.main.card_user.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private lateinit var dataList: List<User>

    fun setData(list: List<User>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.card_user, container, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: User) {
            Glide.with(itemView.context).load(data.avatarUrl).centerCrop().into(itemView.iv_avatar)
            itemView.tv_username.text = data.login
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(data) }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

}