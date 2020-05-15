package dev.arganaphangquestian.github.datasource.network

import dev.arganaphangquestian.github.service.GithubService

class GithubNetwork(private val githubService: GithubService) {
    suspend fun searchUser(username: String) = githubService.searchUser(username)

    suspend fun detailUser(username: String) = githubService.detailUser(username)

    suspend fun followerUser(username: String) = githubService.followerUser(username)

    suspend fun followingUser(username: String) = githubService.followingUser(username)
}