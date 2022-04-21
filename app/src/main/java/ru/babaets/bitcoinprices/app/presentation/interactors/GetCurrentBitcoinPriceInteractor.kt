package ru.babaets.bitcoinprices.app.presentation.interactors

import ru.babaets.bitcoinprices.domain.model.BitcoinPrice
import ru.babaets.bitcoinprices.domain.usecase.GetCurrentBitcoinPriceUseCase
import ru.babaetskv.bitcoinprices.data.api.Api

class GetCurrentBitcoinPriceInteractor(
    private val api: Api
) : GetCurrentBitcoinPriceUseCase {

    override suspend fun execute(): BitcoinPrice = api.getCurrentPrice().toBitcoinPrice()
}
