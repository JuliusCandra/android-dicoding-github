package dev.arganaphangquestian.github.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import dev.arganaphangquestian.github.BuildConfig
import dev.arganaphangquestian.github.service.GithubService
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    private val client = OkHttpClient.Builder()
            .addInterceptor {
                val req =
                    it.request().newBuilder().addHeader("Token", BuildConfig.GITHUB_TOKEN).build()
                return@addInterceptor it.proceed(req)
            }.build()


    private val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideGithubService(): GithubService {
        return retrofit.create(GithubService::class.java)
    }
}