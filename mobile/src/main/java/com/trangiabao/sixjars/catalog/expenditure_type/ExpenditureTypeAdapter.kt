package com.trangiabao.sixjars.catalog.expenditure_type

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.model.ExpenditureType
import kotlinx.android.synthetic.main.item_catalog.view.*

class ExpenditureTypeAdapter(private var listener: ItemClickListener) : RecyclerView.Adapter<ExpenditureTypeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: MutableList<ExpenditureType> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_catalog, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: ExpenditureType = lists[position]
        holder!!.itemView.run {
            txtRevenueType.text = model.type
            txtDescription.text = model.description
            setOnClickListener { listener.onClickListener(model, position) }
            setOnLongClickListener { listener.onLongClickListener(model, position) }
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun updateList(lists: MutableList<ExpenditureType>) {
        this.lists = lists
        notifyDataSetChanged()
    }

    fun addItem(model: ExpenditureType) {
        lists.add(model)
        notifyDataSetChanged()
    }

    fun updateItem(model: ExpenditureType) {
        val newList: MutableList<ExpenditureType> = mutableListOf()
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
        fun onClickListener(type: ExpenditureType, position: Int)
        fun onLongClickListener(type: ExpenditureType, position: Int): Boolean
    }
}