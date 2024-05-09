package features.auth.data.di

import features.auth.data.network.AuthNetworkSource
import features.auth.data.repository.AuthRepository
import features.auth.domain.use_case.AuthUseCase
import org.koin.dsl.module

val authDiModule = module {
    single { AuthNetworkSource(client = get()) }
    single { AuthRepository(source = get()) }
    single { AuthUseCase(authRepository = get()) }
}
