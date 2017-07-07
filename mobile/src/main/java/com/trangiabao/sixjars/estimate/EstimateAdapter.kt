package com.trangiabao.sixjars.estimate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.model.Jar
import kotlinx.android.synthetic.main.item_estimate.view.*
import java.math.BigDecimal
import java.text.DecimalFormat

class EstimateAdapter(private var salary: Double, private var year: Int = 1) : RecyclerView.Adapter<EstimateAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var lists: MutableList<Jar> = mutableListOf()
    private val df = DecimalFormat("###,###,###,###,###.##")

    var List: MutableList<Jar>
        get() = this.lists
        set(value) {
            this.lists = value
            notifyDataSetChanged()
        }

    fun updateYear(year: Int) {
        this.year = year
        notifyDataSetChanged()
    }

    fun updateSalary(income: Double) {
        this.salary = income
        notifyDataSetChanged()
    }

    fun getTotal(): String {
        return df.format(salary * year * 12)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_estimate, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val model: Jar = lists[position]
        holder!!.itemView.run {
            val amount: Double = salary * model.percent / 100 * year * 12
            txtAmount.text = df.format(BigDecimal(amount))
            txtJar.text = model.name
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }
}