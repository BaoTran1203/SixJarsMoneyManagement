package com.trangiabao.sixjars.management.revenue

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.base.model.Revenue
import kotlinx.android.synthetic.main.item_management.view.*
import org.joda.time.DateTime
import java.math.BigDecimal
import java.text.DecimalFormat

class RevenueAdapter(private var listener: ItemClickListener) : RecyclerView.Adapter<RevenueAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: MutableList<Revenue> = mutableListOf()
    private val df = DecimalFormat("###,###,###,###,###.##")

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_management, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Revenue = lists[position]
        holder!!.itemView.run {
            txtJarName.visibility = View.GONE
            txtAmount.text = df.format(BigDecimal(model.amount!!))
            txtRevenueType.text = model.revenueType!!.type!!
            txtDetail.text = model.detail
            val dateFormat = LocaleHelper.getDateFormat(context)
            val timeFormat = LocaleHelper.getTimeFormat(context)
            txtDate.text = dateFormat.print(DateTime(model.date))
            txtTime.text = timeFormat.print(DateTime(model.date))
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