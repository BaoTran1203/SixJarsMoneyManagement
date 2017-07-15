package com.trangiabao.sixjars.ui.dialog.monthpicker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.LocaleHelper
import kotlinx.android.synthetic.main.item_month_picker.view.*

class MonthPickerAdapter(var listener: OnItemClickListener) : RecyclerView.Adapter<MonthPickerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnItemClickListener {
        fun onMonthSelected(month: Int)
    }

    private val MONTH_ENG = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    private val MONTH_VIE = arrayOf("Th 1", "Th 2", "Th 3", "Th 4", "Th 5", "Th 6", "Th 7", "Th 8", "Th 9", "Th 10", "Th 11", "Th 12")
    private var monthSelected: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_month_picker, parent, false))
    }

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.run {
            val language = LocaleHelper.getLanguage(context)
            txtMonth.text = if (language == "vi") MONTH_VIE[position] else MONTH_ENG[position]
            imgMonth.setImageDrawable(resources.getDrawable(R.color.colorSecondaryText))
            txtMonth.setTextColor(resources.getColor(R.color.colorPrimaryText))
            if (monthSelected == position) {
                imgMonth.setImageDrawable(resources.getDrawable(R.color.colorPrimary))
                txtMonth.setTextColor(resources.getColor(R.color.colorSecondaryText))
            }
            setOnClickListener {
                isMonthSelected = position
                listener.onMonthSelected(position + 1)
            }
        }
    }

    override fun getItemCount(): Int {
        return 12
    }

    var isMonthSelected: Int
        get() = this.monthSelected
        set(value) {
            this.monthSelected = value
            notifyDataSetChanged()
        }
}