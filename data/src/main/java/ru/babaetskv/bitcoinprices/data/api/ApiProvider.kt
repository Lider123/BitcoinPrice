package ru.babaetskv.bitcoinprices.data.api

interface ApiProvider {

    fun provideApi(): Api
}
