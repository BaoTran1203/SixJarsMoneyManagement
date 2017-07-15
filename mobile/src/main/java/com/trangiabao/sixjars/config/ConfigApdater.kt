package com.trangiabao.sixjars.config

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.model.Jar
import kotlinx.android.synthetic.main.item_config.view.*

class ConfigApdater : RecyclerView.Adapter<ConfigApdater.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: MutableList<Jar> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_config, parent, false))
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Jar = lists[position]
        holder!!.itemView.run {
            txtName.text = model.name!!
            txtPercent.text = "${model.percent!!}%"
            seekBar.progress = model.percent!!
            seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        if (progress <= 1) {
                            seekBar.progress = 1
                            txtPercent.text = "1%"
                        }
                        model.percent = seekBar.progress
                        txtPercent.text = "${seekBar.progress}%"
                    }
                }
            })
        }
    }

    var List: MutableList<Jar>
        get() = this.lists
        set(value) {
            this.lists = value
            notifyDataSetChanged()
        }
}