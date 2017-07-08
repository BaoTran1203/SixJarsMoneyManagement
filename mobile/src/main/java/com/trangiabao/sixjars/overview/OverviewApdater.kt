package com.trangiabao.sixjars.overview

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.model.Jar
import kotlinx.android.synthetic.main.item_overview.view.*

class OverviewApdater : RecyclerView.Adapter<OverviewApdater.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: List<Jar> = listOf()

    var List: List<Jar>
        get() = this.lists
        set(value) {
            this.lists = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_overview, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Jar = lists[position]
        holder!!.itemView.run {
            txtName.text = model.name
            txtPercent.text = "${model.percent}%"
            if (LocaleHelper.getLanguage(context) == "vi") {
                txtDescription.text = model.description_vie
                txtFullName.text = model.name_vie
            } else {
                txtDescription.text = model.description_eng
                txtFullName.text = model.name_eng
            }
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }
}
