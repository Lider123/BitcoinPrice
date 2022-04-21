package ru.babaetskv.bitcoinprices.data.api

import retrofit2.http.GET
import ru.babaetskv.bitcoinprices.data.model.BitcoinPriceModel

interface Api {

    @GET("bpi/currentprice.json")
    suspend fun getCurrentPrice(): BitcoinPriceModel
}
