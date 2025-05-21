package com.girish.task

import android.app.Application
import com.girish.task.di.mainAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PasswordManagerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PasswordManagerApp)
            androidLogger()
            modules(mainAppModule)
        }
    }
}