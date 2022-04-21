package ru.babaets.bitcoinprices.app.presentation.features.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.babaets.bitcoinprices.domain.model.BitcoinPrice
import ru.babaets.bitcoinprices.domain.usecase.GetCurrentBitcoinPriceUseCase
import kotlin.coroutines.CoroutineContext

class FeedViewModel(
    private val getCurrentBitcoinPriceUseCase: GetCurrentBitcoinPriceUseCase
) : ViewModel(), CoroutineScope {

    private val ticker = tickerFlow(GET_PRICE_DELAY_MILLIS)
        .onEach {
            loadData()
        }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext + Dispatchers.IO

    val pricesLiveData: MutableLiveData<List<BitcoinPrice>> = MutableLiveData(emptyList())

    init {
        ticker.launchIn(this)
    }

    private fun loadData() {
        launch {
            val currList = pricesLiveData.value?.toMutableList() ?: mutableListOf()
            currList.add(0, getCurrentBitcoinPriceUseCase.execute())
            pricesLiveData.postValue(currList.take(MAX_LIST_SIZE))
        }
    }

    private fun tickerFlow(period: Long) = flow {
        while (true) {
            emit(Unit)
            kotlinx.coroutines.delay(period)
        }
    }

    companion object {
        private const val MAX_LIST_SIZE = 50
        private const val GET_PRICE_DELAY_MILLIS = 10000L
    }
}
