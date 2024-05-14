package features.auth.di

import app.network.client.DEFAULT_CLIENT
import app.network.client.buildClient
import features.auth.data.repository.AuthRepository
import features.auth.data.sources.AuthNetworkSource
import features.auth.data.sources.AuthSettingsSource
import features.auth.domain.use_case.AuthUseCase
import org.koin.dsl.module

val authDiModule = module {
    single(qualifier = DEFAULT_CLIENT) { buildClient() }

    single { AuthSettingsSource() }
    single { AuthNetworkSource(client = get(qualifier = DEFAULT_CLIENT)) }
    single { AuthRepository(networkSource = get(), settingsSource = get()) }
    single { AuthUseCase(authRepository = get()) }
}
