package com.trangiabao.sixjars.modules.statistical_bar_chart.presenter

import android.graphics.Color
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.database.ExpenditureDB
import com.trangiabao.sixjars.data.database.JarDB
import com.trangiabao.sixjars.data.database.RevenueDB
import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.modules.statistical_bar_chart.view.BarChartView
import com.trangiabao.sixjars.utils.helper.DateTimeHelper
import org.joda.time.DateTime

class BarChartPresenter(private var view: BarChartView) : BarChartPresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun getData(date: DateTime) {
        val firstDate = DateTimeHelper.getFirstDateOfMonth(date)
        val lastDate = DateTimeHelper.getLastDateOfMonth(date)

        val jars = JarDB.getAll()
        val revenues = RevenueDB.find(firstDate, lastDate)
        val expenditures = ExpenditureDB.find(firstDate, lastDate)

        if (revenues == null || expenditures == null)
            view.onError(R.string.app_name)
        else if (revenues.isEmpty() && expenditures.isEmpty()) {
            view.onWarning(R.string.app_name)
        } else {
            val labels = getXValues(jars)
            val yValues1 = getYValues1(jars, revenues)
            val yValues2 = getYValues2(jars, expenditures)

            val barData = BarData(yValues1, yValues2)
            barData.setValueFormatter(LargeValueFormatter())

            view.onGetDataSuccessed(labels, barData)
        }
    }

    private fun getXValues(jars: List<Jar>): List<String> {
        val xValues: MutableList<String> = mutableListOf()
        jars.mapTo(xValues) { it.name!! }
        return xValues.toList()
    }

    private fun getYValues1(jars: List<Jar>, revenue: List<Revenue>): BarDataSet {
        val yValues = mutableListOf<BarEntry>()
        val sum: Double = revenue.sumByDouble { it.amount!! }
        (0..jars.size - 1).mapTo(yValues) {
            BarEntry(it.toFloat(), (sum * jars[it].percent!! / 100).toFloat())
        }
        val barDataSet: BarDataSet = BarDataSet(yValues, "")
        barDataSet.color = Color.rgb(241, 196, 15)
        return barDataSet
    }

    private fun getYValues2(jars: List<Jar>, expenditure: List<Expenditure>): BarDataSet {
        val lstPair = mutableListOf<Pair<String, Float>>()
        for (jar in jars) {
            val jarName: String = jar.name!!
            val total = expenditure.filter { it.jar!!.name!! == jarName }.sumByDouble { it.amount!! }
            val pair = Pair(jarName, total.toFloat())
            lstPair.add(pair)
        }
        val map = lstPair.map { it.first to it.second }.toMap()

        val yValues = mutableListOf<BarEntry>()
        var i: Int = 0
        for ((_, value) in map) {
            yValues.add(BarEntry((i++).toFloat(), value))
        }
        val barDataSet: BarDataSet = BarDataSet(yValues, "")
        barDataSet.color = Color.rgb(52, 152, 219)
        return barDataSet
    }
}