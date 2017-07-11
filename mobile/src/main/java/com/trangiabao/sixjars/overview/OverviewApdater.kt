package com.trangiabao.sixjars.overview

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.base.model.Jar
import kotlinx.android.synthetic.main.item_overview.view.*

class OverviewApdater : RecyclerView.Adapter<OverviewApdater.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: List<Jar> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_overview, parent, false))
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Jar = lists[position]
        holder!!.itemView.run {
            txtName.text = model.name
            txtPercent.text = "${model.percent}%"
            if (LocaleHelper.getLanguage(context) == "vi") {
                txtDescription.text = model.descriptionVie
                txtFullName.text = model.nameVie
            } else {
                txtDescription.text = model.descriptionEng
                txtFullName.text = model.nameEng
            }
        }
    }

    var List: List<Jar>
        get() = this.lists
        set(value) {
            this.lists = value
            notifyDataSetChanged()
        }
}
