package dev.arganaphangquestian.github.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "users")
@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,
    @ColumnInfo(name = "avatar_url")
    @Json(name = "avatar_url")
    var avatarUrl: String = "",
    @ColumnInfo(name = "login")
    @Json(name = "login")
    var login: String = "",
    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean = false
)