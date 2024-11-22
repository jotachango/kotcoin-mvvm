package com.jnfran92.kotcoin.crypto.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.crypto.presentation.model.UICrypto
import com.jnfran92.kotcoin.databinding.ViewCryptoItemBinding
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

/**
 * Adapter for Crypto data and RecyclerView UI Element
 */
@FragmentScoped
class CryptoListAdapter @Inject constructor(
    @ActivityContext private val context: Context
) : RecyclerView.Adapter<CryptoListAdapter.CryptoViewHolder>() {

    /**
     * Listener
     */
    private var onClickItemListener: ((UICrypto) -> Unit)? = null
    fun setListener(listener: (UICrypto) -> Unit) {
        onClickItemListener = listener
    }

    /**
     * Data list
     */
    private var cryptoList: ArrayList<UICrypto> = ArrayList()

    /**
     * DataBinding
     */
    lateinit var binding: ViewCryptoItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        binding = ViewCryptoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val crypto = cryptoList[position]
        holder.itemName.text = crypto.name
        holder.itemSymbol.text = crypto.symbol

        val price: String = context.getText(R.string.money_symbol).toString() +
                "%,.3f".format(crypto.usdPrice.price)
        holder.itemPrice.text = price

//        val marketCap = context.getText(R.string.money_symbol).toString() +
//                "%,.2f".format(crypto.mar / 10e9) +
//                "MM"
//        holder.itemMarketCap.text = marketCap

//        val lastUpdate: String = context.getString(R.string.updated_at) + " " +
//                crypto.lastUpdated
////        holder.itemLastUpdate.text = lastUpdate

        holder.container.setOnClickListener { onClickItemListener?.invoke(crypto) }
    }


    fun setData(data: List<UICrypto>) {
        this.cryptoList.clear()
        this.cryptoList.addAll(data)
        this.notifyDataSetChanged()
    }

    class CryptoViewHolder(itemView: ViewCryptoItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val itemName: TextView = itemView.tvCryptoItemName
        val itemSymbol: TextView = itemView.tvCryptoItemSymbol
        val itemPrice: TextView = itemView.tvCryptoItemPrice

        //        val itemMarketCap: TextView = itemView.tv_cryptoItem_markertCap
//        val itemLastUpdate: TextView = itemView.tv_cryptoItem_lastUpdate
        val container: MaterialCardView = itemView.mcCryptoItemContainer
    }
}