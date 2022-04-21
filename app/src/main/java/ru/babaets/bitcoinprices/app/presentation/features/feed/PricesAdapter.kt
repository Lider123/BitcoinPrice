package ru.babaets.bitcoinprices.app.presentation.features.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.babaets.bitcoinprices.app.databinding.ViewItemPriceBinding
import ru.babaets.bitcoinprices.domain.model.BitcoinPrice

class PricesAdapter : ListAdapter<BitcoinPrice, PricesAdapter.ViewHolder>(PricesDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater.from(parent.context)
            .let {
                ViewItemPriceBinding.inflate(it, parent, false)
            }
            .let(::ViewHolder)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ViewItemPriceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BitcoinPrice) {
            binding.run {
                tvPrice.text = item.currencies[0].toString()
                tvTimestamp.text = item.updated
            }
        }
    }

    private class PricesDiffUtilCallback : DiffUtil.ItemCallback<BitcoinPrice>() {

        override fun areItemsTheSame(oldItem: BitcoinPrice, newItem: BitcoinPrice): Boolean =
            oldItem.updatedISO == newItem.updatedISO

        override fun areContentsTheSame(oldItem: BitcoinPrice, newItem: BitcoinPrice): Boolean =
            oldItem == newItem
    }
}
