package dev.arganaphangquestian.github.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.arganaphangquestian.github.data.entity.User
import dev.arganaphangquestian.github.datasource.repository.GithubRepository
import dev.arganaphangquestian.github.utils.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class HomeViewModel(private val repo: GithubRepository) : ViewModel() {
    val searchText = MutableLiveData("")
    val networkState = MutableLiveData<NetworkState>()

    var users = MutableLiveData<List<User>>()

    fun refereshSearch(username: String) {
        viewModelScope.launch {
            networkState.value = NetworkState.LOADING
            try {
                val res = repo.searchUser(username).items
                withContext(Dispatchers.Main) {
                    users.value = res
                    networkState.value = NetworkState.LOADED
                }
            } catch (e: Exception) {
                Timber.e(e)
                networkState.value = NetworkState.error(e.message)
            }
        }
    }

}