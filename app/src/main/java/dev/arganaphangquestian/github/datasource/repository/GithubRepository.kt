package dev.arganaphangquestian.github.datasource.repository

import dev.arganaphangquestian.github.data.entity.User
import dev.arganaphangquestian.github.datasource.local.GithubLocal
import dev.arganaphangquestian.github.datasource.network.GithubNetwork
import javax.inject.Inject

class GithubRepository @Inject constructor(private val local: GithubLocal, private val network: GithubNetwork) {
    suspend fun searchUser(username: String) = network.searchUser(username)

    suspend fun detailUser(username: String) = network.detailUser(username)

    suspend fun followerUser(username: String) = network.followerUser(username)

    suspend fun followingUser(username: String) = network.followingUser(username)

    fun addFavourite(user: User) = local.save(user)

    fun deleteFavourite(user: User) = local.delete(user)

    fun getFavourites() = local.getAll()

    fun findFavouriteByUsername(username: String) = local.findByUsername(username)
}