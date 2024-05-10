package features.contexts.di

import features.contexts.data.repository.ContextRepository
import features.contexts.data.sources.ContextNetworkSource
import features.contexts.domain.use_case.ContextUseCase
import org.koin.dsl.module

val contextsDiModule = module {
    single { ContextNetworkSource(client = get()) }
    single { ContextRepository(networkSource = get()) }
    single { ContextUseCase(repository = get()) }
}
