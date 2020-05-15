package dev.arganaphangquestian.github.service

import dev.arganaphangquestian.github.data.entity.DetailUser
import dev.arganaphangquestian.github.data.entity.User
import dev.arganaphangquestian.github.data.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("/search/users")
    suspend fun searchUser(@Query("q") username: String): SearchResponse

    @GET("/users/{username}")
    suspend fun detailUser(@Path("username") username: String): DetailUser

    @GET("/users/{username}/followers")
    suspend fun followerUser(@Path("username") username: String): List<User>

    @GET("/users/{username}/following")
    suspend fun followingUser(@Path("username") username: String): List<User>
}