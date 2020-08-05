package dev.arganaphangquestian.github.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.arganaphangquestian.github.datasource.repository.GithubRepository
import dev.arganaphangquestian.github.utils.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DetailViewModel @ViewModelInject constructor(private val repo: GithubRepository) :
    ViewModel() {

    val networkState = MutableLiveData<NetworkState>()

    fun getDetails(username: String): MutableLiveData<Map<String, Any>> {
        val details = MutableLiveData<Map<String, Any>>()
        viewModelScope.launch {
            networkState.value = NetworkState.LOADING
            try {
                val detailResult = repo.detailUser(username)
                val followerResult = repo.followerUser(username)
                val followingResult = repo.followingUser(username)
                withContext(Dispatchers.Main) {
                    networkState.value = NetworkState.LOADED
                    details.value = mapOf(
                        "detail" to detailResult,
                        "follower" to followerResult,
                        "following" to followingResult
                    )
                }
            } catch (e: Exception) {
                networkState.value = NetworkState.error(e.message)
                Timber.e(e)
            }
        }
        return details
    }

}