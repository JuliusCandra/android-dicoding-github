package dev.arganaphangquestian.github.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.arganaphangquestian.github.data.entity.DetailUser
import dev.arganaphangquestian.github.data.entity.User
import dev.arganaphangquestian.github.datasource.repository.GithubRepository
import dev.arganaphangquestian.github.utils.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DetailViewModel @ViewModelInject constructor(private val repo: GithubRepository) :
    ViewModel() {
    val networkState = MutableLiveData<NetworkState>()
    val details = MutableLiveData<Map<String, Any>>()
    val usernameGithub = MutableLiveData("")

    fun getDetails(username: String): MutableLiveData<NetworkState> {
        viewModelScope.launch {
            networkState.value = NetworkState.LOADING
            try {
                val detailResult = repo.detailUser(username)
                val followerResult = repo.followerUser(username)
                val followingResult = repo.followingUser(username)
                withContext(Dispatchers.Main) {
                    details.value = mapOf(
                        "detail" to detailResult,
                        "follower" to followerResult,
                        "following" to followingResult
                    )
                }
                networkState.value = NetworkState.LOADED
            } catch (e: Exception) {
                networkState.value = NetworkState.error(e.message)
                Timber.e(e)
            }
        }
        return networkState
    }

    fun isFavourite() : MutableLiveData<User?>{
        val res = MutableLiveData<User?>()
        viewModelScope.launch {
            val resUser = usernameGithub.value?.let { repo.findFavouriteByUsername(it) }
            withContext(Dispatchers.Main) {
                if (resUser != null) {
                    res.value = resUser.value
                }
            }
        }
        return res
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            networkState.value = NetworkState.LOADING
            try {
                val resFav = repo.findFavouriteByUsername(usernameGithub.value!!)
                withContext(Dispatchers.Main) {
                    if (resFav.value != null) {
                        repo.deleteFavourite(resFav.value!!)
                    } else {
                        val detail = details.value?.get("detail") as DetailUser
                        repo.addFavourite(User(id = detail.id, avatarUrl = detail.avatarUrl, login = detail.login, isFavourite = true))
                    }
                    networkState.value = NetworkState.LOADED
                }
            } catch (e: Exception) {
                networkState.value = NetworkState.error(e.message)
                Timber.e(e)
            }
        }
    }

}