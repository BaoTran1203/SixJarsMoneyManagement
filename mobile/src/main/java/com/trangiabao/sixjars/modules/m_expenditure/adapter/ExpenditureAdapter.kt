package com.trangiabao.sixjars.modules.m_expenditure.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.utils.helper.DateTimeHelper
import com.trangiabao.sixjars.utils.helper.NumberHelper
import kotlinx.android.synthetic.main.item_management.view.*
import org.joda.time.DateTime

class ExpenditureAdapter : RecyclerView.Adapter<ExpenditureAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var listener: ItemClickListener? = null
    private var lists: MutableList<Expenditure> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_management, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Expenditure = lists[position]
        holder!!.itemView.run {
            txtCurrentAmount.text = NumberHelper.printBigDouble(model.amount!!)
            txtRevenueType.text = model.expenditureType!!.type!!
            txtDetail.text = model.detail!!
            txtJarName.text = model.jar!!.name!!
            val dateFormat = DateTimeHelper.getDateFormat(context)
            val timeFormat = DateTimeHelper.getTimeFormat(context)
            txtDate.text = dateFormat.print(DateTime(model.date))
            txtTime.text = timeFormat.print(DateTime(model.date))
            setOnClickListener { listener!!.onClickListener(model, position) }
            setOnLongClickListener { listener!!.onLongClickListener(model, position) }
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }

    fun updateList(lists: MutableList<Expenditure>) {
        this.lists = lists
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        lists.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, lists.size)
    }

    interface ItemClickListener {
        fun onClickListener(type: Expenditure, position: Int)
        fun onLongClickListener(type: Expenditure, position: Int): Boolean
    }
}