package dev.arganaphangquestian.github

import android.app.Application
import dev.arganaphangquestian.github.di.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@InternalCoroutinesApi
@Suppress("unused")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    retrofitModule,
                    serviceModule,
                    databaseModule,
                    localModule,
                    networkModule
                )
            )
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}