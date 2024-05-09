package app.di

import app.network.client.client
import org.koin.dsl.module

fun appModule() = module {
    single { client }
}
