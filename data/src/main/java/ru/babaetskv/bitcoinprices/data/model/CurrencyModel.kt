package ru.babaetskv.bitcoinprices.data.model

import com.squareup.moshi.Json
import ru.babaets.bitcoinprices.domain.model.Currency

data class CurrencyModel(
    @Json(name = "code") val code: String,
    @Json(name = "rate_float") val price: Float
) {

    fun toCurrency(): Currency =
        Currency(
            code = code,
            price = price
        )
}