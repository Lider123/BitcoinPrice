package ru.babaetskv.bitcoinprices.data.model

import com.squareup.moshi.Json

data class BpiModel(
    @Json(name = "USD") val dollarCurrency: CurrencyModel
)
