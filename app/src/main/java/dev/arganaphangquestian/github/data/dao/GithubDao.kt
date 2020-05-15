package dev.arganaphangquestian.github.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.arganaphangquestian.github.data.entity.User

@Dao
interface GithubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users")
    fun findAll(): LiveData<List<User>>
}