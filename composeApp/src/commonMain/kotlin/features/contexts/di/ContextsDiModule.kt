package features.contexts.di

import app.network.client.AUTH_CLIENT
import app.network.client.buildAuthClient
import features.contexts.data.repository.AnsweringRepository
import features.contexts.data.repository.ContextRepository
import features.contexts.data.sources.AnsweringNetworkSource
import features.contexts.data.sources.ContextDbSource
import features.contexts.data.sources.ContextNetworkSource
import features.contexts.domain.use_case.AnsweringUseCase
import features.contexts.domain.use_case.ContextUseCase
import org.koin.dsl.module

val contextsDiModule = module {
    single(qualifier = AUTH_CLIENT) { buildAuthClient(authSource = get()) }

    single { ContextDbSource(db = get()) }
    single { ContextNetworkSource(client = get(qualifier = AUTH_CLIENT)) }
    single {
        ContextRepository(
            networkSource = get(),
            dbSource = get()
        )
    }
    single { ContextUseCase(repository = get()) }

    single { AnsweringNetworkSource(client = get(qualifier = AUTH_CLIENT)) }
    single { AnsweringRepository(source = get()) }
    single { AnsweringUseCase(repository = get(), contextRepository = get()) }
}
