package ru.babaets.bitcoinprices.app.presentation.features.feed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.babaets.bitcoinprices.app.databinding.ActivityMainBinding
import ru.babaets.bitcoinprices.domain.model.BitcoinPrice

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: FeedViewModel by viewModel()

    private val pricesAdapter: PricesAdapter by lazy {
        PricesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvPrices.adapter = pricesAdapter

        viewModel.pricesLiveData.observe(this, ::populatePrices)
    }

    private fun populatePrices(prices: List<BitcoinPrice>) {
        pricesAdapter.submitList(prices)
    }
}
