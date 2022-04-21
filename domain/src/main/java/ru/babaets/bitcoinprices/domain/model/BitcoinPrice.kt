package ru.babaets.bitcoinprices.domain.model

data class BitcoinPrice(
    val currencies: List<Currency>,
    val updated: String,
    val updatedISO: String,
)
