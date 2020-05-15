package dev.arganaphangquestian.github.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.arganaphangquestian.github.data.entity.User

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "incomplete_results")
    var incompleteResults: Boolean = false,
    @Json(name = "items")
    var items: List<User> = listOf(),
    @Json(name = "total_count")
    var totalCount: Int = 0
)