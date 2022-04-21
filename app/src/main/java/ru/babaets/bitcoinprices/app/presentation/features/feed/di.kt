package ru.babaets.bitcoinprices.app.presentation.features.feed

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.babaets.bitcoinprices.app.presentation.interactors.GetCurrentBitcoinPriceInteractor
import ru.babaets.bitcoinprices.domain.usecase.GetCurrentBitcoinPriceUseCase

val feedModule = module {

    viewModel {
        FeedViewModel(
            getCurrentBitcoinPriceUseCase = get()
        )
    }

    factory<GetCurrentBitcoinPriceUseCase> {
        GetCurrentBitcoinPriceInteractor(
            api = get()
        )
    }
}