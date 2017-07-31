package com.trangiabao.sixjars.modules.catalog_expenditure_type.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.ExpenditureType
import kotlinx.android.synthetic.main.item_catalog.view.*

class ExpenditureTypeAdapter : RecyclerView.Adapter<ExpenditureTypeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: MutableList<ExpenditureType> = mutableListOf()
    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_catalog, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: ExpenditureType = lists[position]
        holder!!.itemView.run {
            txtRevenueType.text = model.type
            txtDescription.text = model.description
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

    fun updateList(lists: MutableList<ExpenditureType>) {
        this.lists = lists
        notifyDataSetChanged()
    }

    fun updateItem(model: ExpenditureType) {
        for (i in 0..lists.size - 1) {
            if (lists[i].id == model.id) {
                lists[i] = model
                notifyItemChanged(i)
                notifyItemRangeChanged(i, lists.size)
                return
            }
        }

        lists.add(model)
        notifyItemInserted(lists.size)
        notifyItemRangeChanged(lists.size - 1, lists.size)
    }

    fun removeItem(position: Int) {
        lists.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, lists.size)
    }

    interface ItemClickListener {
        fun onClickListener(obj: ExpenditureType, position: Int)
        fun onLongClickListener(obj: ExpenditureType, position: Int): Boolean
    }
}
