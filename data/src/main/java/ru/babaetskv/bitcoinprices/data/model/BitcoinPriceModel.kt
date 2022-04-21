package ru.babaetskv.bitcoinprices.data.model

import com.squareup.moshi.Json
import ru.babaets.bitcoinprices.domain.model.BitcoinPrice

data class BitcoinPriceModel(
    @Json(name = "time") val bitcoinUpdateTimeModel: BitcoinUpdateTimeModel,
    @Json(name = "bpi") val bpi: BpiModel
) {

    fun toBitcoinPrice(): BitcoinPrice =
        BitcoinPrice(
            currencies = listOf(bpi.dollarCurrency.toCurrency()),
            updated = bitcoinUpdateTimeModel.updated,
            updatedISO = bitcoinUpdateTimeModel.updatedISO
        )
}
