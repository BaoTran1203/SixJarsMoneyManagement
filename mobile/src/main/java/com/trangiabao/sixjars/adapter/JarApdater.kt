package com.trangiabao.sixjars.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import android.view.View
import com.trangiabao.sixjars.model.Jar
import com.trangiabao.sixjars.system.LocaleHelper
import io.realm.RealmResults
import kotlinx.android.synthetic.main.item_jar.view.*

class JarApdater : RecyclerView.Adapter<JarApdater.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: RealmResults<Jar>? = null

    fun updateList(lists: RealmResults<Jar>) {
        this.lists = lists
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_jar, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Jar = lists!![position]
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

    override fun getItemCount(): Int {
        return lists!!.size
    }
}
