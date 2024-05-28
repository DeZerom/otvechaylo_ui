package ru.dezerom.otvechaylo

import android.app.Application
import di.dbDiModule
import features.auth.di.authDiModule
import features.contexts.di.contextsDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OtvechayloApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@OtvechayloApp)
            modules(authDiModule, dbDiModule, contextsDiModule)
        }
    }
}
