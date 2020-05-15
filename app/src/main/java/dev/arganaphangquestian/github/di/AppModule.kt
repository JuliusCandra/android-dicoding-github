package dev.arganaphangquestian.github.di

import android.app.Application
import androidx.room.Room
import dev.arganaphangquestian.github.AppDatabase
import dev.arganaphangquestian.github.BuildConfig
import dev.arganaphangquestian.github.data.dao.GithubDao
import dev.arganaphangquestian.github.datasource.local.GithubLocal
import dev.arganaphangquestian.github.datasource.network.GithubNetwork
import dev.arganaphangquestian.github.datasource.repository.GithubRepository
import dev.arganaphangquestian.github.service.GithubService
import dev.arganaphangquestian.github.ui.detail.DetailViewModel
import dev.arganaphangquestian.github.ui.favourite.FavouriteViewModel
import dev.arganaphangquestian.github.ui.home.HomeViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val viewModelModule = module {
    viewModel { DetailViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
}

val serviceModule = module {
    fun provideUserApi(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }
    single { provideUserApi(get()) }
}

val retrofitModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor {
                val req =
                    it.request().newBuilder().addHeader("Token", BuildConfig.GITHUB_TOKEN).build()
                return@addInterceptor it.proceed(req)
            }

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }
    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "github.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideGithubDao(database: AppDatabase): GithubDao {
        return database.githubDao()
    }
    single { provideDatabase(androidApplication()) }
    single { provideGithubDao(get()) }
}

val localModule = module {
    fun provideGithubLocal(dao: GithubDao): GithubLocal {
        return GithubLocal(dao)
    }

    single { provideGithubLocal(get()) }
}

val networkModule = module {

    fun provideGithubNetwork(service: GithubService): GithubNetwork {
        return GithubNetwork(service)
    }

    single { provideGithubNetwork(get()) }
}

val repositoryModule = module {
    fun provideUserRepository(local: GithubLocal, network: GithubNetwork): GithubRepository {
        return GithubRepository(local, network)
    }

    single { provideUserRepository(get(), get()) }
}