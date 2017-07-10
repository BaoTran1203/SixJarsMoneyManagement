package com.trangiabao.sixjars.management.revenue

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.model.Revenue
import kotlinx.android.synthetic.main.item_revenue.view.*
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class RevenueAdapter(private var listener: ItemClickListener) : RecyclerView.Adapter<RevenueAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: MutableList<Revenue> = mutableListOf()
    private val df = DecimalFormat("###,###,###,###,###.##")

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_revenue, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Revenue = lists[position]
        holder!!.itemView.run {
            txtAmount.text = df.format(BigDecimal(model.amount!!))
            txtRevenueType.text = model.revenueType!!.type!!
            txtDetail.text = model.detail
            val dateFormat = SimpleDateFormat(context.getString(R.string.date_format), Locale.US)
            val timeFormat = SimpleDateFormat(context.getString(R.string.time_format), Locale.US)
            txtDate.text = dateFormat.format(model.date)
            txtTime.text = timeFormat.format(model.date)
            setOnClickListener { listener.onClickListener(model, position) }
            setOnLongClickListener { listener.onLongClickListener(model, position) }
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun updateList(lists: MutableList<Revenue>) {
        this.lists = lists
        notifyDataSetChanged()
    }

    fun addItem(model: Revenue) {
        lists.add(model)
        notifyDataSetChanged()
    }

    fun updateItem(model: Revenue) {
        val newList: MutableList<Revenue> = mutableListOf()
        for (item in lists) {
            if (item.id == model.id)
                newList.add(model)
            else
                newList.add(item)
        }
        lists = newList
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        lists.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, lists.size)
    }

    interface ItemClickListener {
        fun onClickListener(type: Revenue, position: Int)
        fun onLongClickListener(type: Revenue, position: Int): Boolean
    }
}