package com.yunchen.m09

import android.app.Application
import com.yunchen.m09.repository.repositoryModule
import com.yunchen.m09.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class M07Application : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@M07Application)

            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}