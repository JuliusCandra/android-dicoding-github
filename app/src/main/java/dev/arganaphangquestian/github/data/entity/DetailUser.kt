package dev.arganaphangquestian.github.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailUser(
    @Json(name = "id")
    var id: Long = 0L,
    @Json(name = "name")
    var name: String = "",
    @Json(name = "avatar_url")
    var avatarUrl: String = "",
    @Json(name = "login")
    var login: String = "",
    @Json(name = "company")
    var company: String? = "",
    @Json(name = "blog")
    var blog: String? = "",
    @Json(name = "location")
    var location: String? = ""
)