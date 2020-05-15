package dev.arganaphangquestian.github.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

object CustomBindingAdapter {
    @BindingAdapter("showLoading")
    @JvmStatic
    fun setProgressBarVisibility(view: ProgressBar, visible: MutableLiveData<NetworkState>) {
        view.visibility =
            if (visible.value != null && visible.value!!.status == NetworkState.Status.RUNNING) View.VISIBLE else View.GONE
    }

    @BindingAdapter("showList")
    @JvmStatic
    fun setRecyclerViewVisibility(view: RecyclerView, visible: MutableLiveData<NetworkState>) {
        view.visibility =
            if (visible.value != null && visible.value!!.status == NetworkState.Status.SUCCESS) View.VISIBLE else View.GONE
    }

    @BindingAdapter(value = ["showEmptyText", "showVisible"], requireAll = false)
    @JvmStatic
    fun setTextViewVisibility(view: TextView, text: MutableLiveData<String>, visible: Boolean) {
        view.visibility = if (text.value.isNullOrEmpty() || visible) View.VISIBLE else View.GONE
    }
}