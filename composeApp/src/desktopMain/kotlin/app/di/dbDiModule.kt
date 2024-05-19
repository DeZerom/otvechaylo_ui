package app.di

import app.db.DbDriverFactory
import app.db.createDatabase
import org.koin.dsl.module

val dbDiModule = module {
    single { DbDriverFactory() }
    single { createDatabase(get()) }
}
