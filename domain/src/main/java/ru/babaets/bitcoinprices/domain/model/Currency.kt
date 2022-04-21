package ru.babaets.bitcoinprices.domain.model

data class Currency(
    val code: String,
    val price: Float
) {

    override fun toString(): String = "%.2f $code".format(price)
}
