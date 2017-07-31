package com.trangiabao.sixjars.modules.wallet.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Wallet
import com.trangiabao.sixjars.utils.helper.NumberHelper
import kotlinx.android.synthetic.main.item_wallet.view.*

class WalletAdapter : RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: List<Wallet> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_wallet, parent, false))
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Wallet = lists[position]
        holder!!.itemView.run {
            txtJarName.text = model.jar!!.name!!
            txtCurrentAmount.text = NumberHelper.printBigDouble(model.currentAmout!!)
        }
    }

    var List: List<Wallet>
        get() = this.lists
        set(value) {
            this.lists = value
            notifyDataSetChanged()
        }
}