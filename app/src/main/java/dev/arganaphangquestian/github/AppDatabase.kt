package dev.arganaphangquestian.github

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.arganaphangquestian.github.data.dao.GithubDao
import dev.arganaphangquestian.github.data.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun githubDao(): GithubDao

    companion object {
        fun newInstance() = Room.databaseBuilder(
            App.instance.applicationContext,
            AppDatabase::class.java, "github.db"
        ).build()
    }
}