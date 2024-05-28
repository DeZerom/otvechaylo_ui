package di

import app.db.DbDriverFactory
import app.db.createDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbDiModule = module {
    single { DbDriverFactory(context = androidContext()) }
    single { createDatabase(get()) }
}
