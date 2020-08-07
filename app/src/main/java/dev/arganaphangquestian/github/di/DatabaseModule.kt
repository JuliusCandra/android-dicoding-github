package dev.arganaphangquestian.github.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.arganaphangquestian.github.App
import dev.arganaphangquestian.github.AppDatabase
import dev.arganaphangquestian.github.data.dao.GithubDao
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase() = Room.databaseBuilder(App.instance.applicationContext, AppDatabase::class.java, "github.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideGithubDao(appDatabase: AppDatabase): GithubDao {
        return appDatabase.githubDao()
    }
}