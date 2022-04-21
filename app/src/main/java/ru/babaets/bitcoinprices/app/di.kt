package ru.babaets.bitcoinprices.app

import org.koin.dsl.module
import ru.babaetskv.bitcoinprices.data.api.ApiProvider
import ru.babaetskv.bitcoinprices.data.api.ApiProviderImpl

val networkModule = module {
    single<ApiProvider> { ApiProviderImpl() }
    single { get<ApiProvider>().provideApi() }
}