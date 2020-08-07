package dev.arganaphangquestian.github.ui.favourite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dev.arganaphangquestian.github.data.entity.User
import dev.arganaphangquestian.github.datasource.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel @ViewModelInject constructor(private val repo: GithubRepository) :
    ViewModel() {

    var favourites = repo.getFavourites().map { data ->
        data.reversed().map { User(it.id, it.avatarUrl, it.login) }
    }
}