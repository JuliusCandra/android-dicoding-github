package dev.arganaphangquestian.github.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.data.entity.User

@BindingAdapter("showLoading")
fun setProgressBarVisibility(view: ProgressBar, visible: MutableLiveData<NetworkState>) {
    view.visibility =
        if (visible.value != null && visible.value!!.status == NetworkState.Status.RUNNING) View.VISIBLE else View.GONE
}

@BindingAdapter("showList")
fun setRecyclerViewVisibility(view: RecyclerView, visible: MutableLiveData<NetworkState>) {
    view.visibility =
        if (visible.value != null && visible.value!!.status == NetworkState.Status.SUCCESS) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["showEmptyText", "showVisible"], requireAll = false)
fun setTextViewVisibility(view: TextView, text: MutableLiveData<String>, visible: Boolean) {
    view.visibility = if (text.value.isNullOrEmpty() || visible) View.VISIBLE else View.GONE
}

@BindingAdapter("isFavourite")
fun isFavouriteFAB(view: FloatingActionButton, visible: MutableLiveData<User?>) {
    if (visible.value != null) {
        view.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.ic_favourite_fill))
    } else {
        view.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.ic_favourite))
    }
}