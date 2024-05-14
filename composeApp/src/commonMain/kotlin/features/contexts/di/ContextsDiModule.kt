package features.contexts.di

import app.network.client.AUTH_CLIENT
import app.network.client.buildAuthClient
import features.contexts.data.repository.ContextRepository
import features.contexts.data.sources.ContextNetworkSource
import features.contexts.domain.use_case.ContextUseCase
import org.koin.dsl.module

val contextsDiModule = module {
    single(qualifier = AUTH_CLIENT) { buildAuthClient(authSource = get()) }

    single { ContextNetworkSource(client = get(qualifier = AUTH_CLIENT)) }
    single { ContextRepository(networkSource = get()) }
    single { ContextUseCase(repository = get()) }
}
