package com.trangiabao.sixjars.config

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.model.Jar
import kotlinx.android.synthetic.main.item_config.view.*

class ConfigApdater : RecyclerView.Adapter<ConfigApdater.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: MutableList<Jar> = mutableListOf()

    var List: MutableList<Jar>
        get() = this.lists
        set(value) {
            this.lists = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_config, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Jar = lists[position]
        holder!!.itemView.run {
            txtName.text = model.name
            numberPickerPercent.minValue = 0
            numberPickerPercent.value = model.percent!!
            numberPickerPercent.maxValue = 100
            numberPickerPercent.setOnValueChangedListener { _, _, newVal ->
                model.percent = newVal
            }
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }
}