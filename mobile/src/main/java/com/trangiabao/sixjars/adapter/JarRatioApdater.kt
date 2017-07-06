package com.trangiabao.sixjars.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.model.Jar
import kotlinx.android.synthetic.main.item_jar_config.view.*

class JarRatioApdater : RecyclerView.Adapter<JarRatioApdater.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: MutableList<Jar> = mutableListOf()

    var List: MutableList<Jar>
        get() = this.lists
        set(value) {
            this.lists = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.item_jar_config, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Jar = lists[position]
        holder!!.itemView.run {
            txtName.text = model.name
            numberPickerPercent.minValue = 0
            numberPickerPercent.value = model.percent
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