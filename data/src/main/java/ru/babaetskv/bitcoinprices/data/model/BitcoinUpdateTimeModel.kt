package ru.babaetskv.bitcoinprices.data.model

import com.squareup.moshi.Json

data class BitcoinUpdateTimeModel(
    @Json(name = "updated") val updated: String,
    @Json(name = "updatedISO") val updatedISO: String
)
