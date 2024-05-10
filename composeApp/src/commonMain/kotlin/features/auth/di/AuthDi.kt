package features.auth.di

import features.auth.data.repository.AuthRepository
import features.auth.data.sources.AuthNetworkSource
import features.auth.data.sources.AuthSettingsSource
import features.auth.domain.use_case.AuthUseCase
import org.koin.dsl.module

val authDiModule = module {
    single { AuthSettingsSource() }
    single { AuthNetworkSource(client = get()) }
    single { AuthRepository(networkSource = get(), settingsSource = get()) }
    single { AuthUseCase(authRepository = get()) }
}
