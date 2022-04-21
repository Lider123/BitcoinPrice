package ru.babaets.bitcoinprices.domain.usecase

import ru.babaets.bitcoinprices.domain.model.BitcoinPrice

interface GetCurrentBitcoinPriceUseCase {

    suspend fun execute(): BitcoinPrice
}
