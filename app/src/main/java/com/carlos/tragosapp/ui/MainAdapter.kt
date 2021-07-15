package com.carlos.tragosapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlos.tragosapp.R
import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.databinding.ItemDrinkBinding
import com.carlos.tragosapp.ui.base.BaseViewHolder

class MainAdapter(
    private val context: Context,
    private val itemClickListener: OnDrinkClickListener
) : ListAdapter<Drink,BaseViewHolder<Drink>>(DiffCallback()) {

    interface OnDrinkClickListener {
        fun onDrinkClick(drink: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Drink> {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Drink>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(getItem(position))
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {

        val binding = ItemDrinkBinding.bind(itemView)

        override fun bind(item: Drink) {
            binding.apply {
                tvTitulo.text = item.nombre
                tvDescription.text = item.description
                Glide.with(context)
                    .load(item.imagen)
                    .centerCrop()
                    .into(imgTrago)
                itemView.setOnClickListener { itemClickListener.onDrinkClick(item) }
            }
        }

    }
}

class DiffCallback : DiffUtil.ItemCallback<Drink>() {
    override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
        return oldItem.drinkId == newItem.drinkId
    }

    override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
        return oldItem == newItem
    }
}
