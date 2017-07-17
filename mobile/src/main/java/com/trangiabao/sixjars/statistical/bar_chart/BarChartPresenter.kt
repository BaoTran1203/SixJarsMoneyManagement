package com.trangiabao.sixjars.statistical.bar_chart

import android.graphics.Color
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.trangiabao.sixjars.base.database.ExpenditureDB
import com.trangiabao.sixjars.base.database.JarDB
import com.trangiabao.sixjars.base.database.RevenueDB
import com.trangiabao.sixjars.base.model.Expenditure
import com.trangiabao.sixjars.base.model.Jar
import com.trangiabao.sixjars.base.model.Revenue
import org.joda.time.DateTime
import java.util.*

class BarChartPresenter(private var view: BarChartView) : BarChartPresenterImpl {

    override fun getData(date: DateTime) {
        val firstDate = getFirstDateOfMonth(date)
        val lastDate = getLastDateOfMonth(date)

        val jars = JarDB.getAll()
        val revenues = RevenueDB.find(firstDate, lastDate)
        val expenditures = ExpenditureDB.find(firstDate, lastDate)

        val labels = getXValues(jars)
        val yValues1 = getYValues1(jars, revenues)
        val yValues2 = getYValues2(jars, expenditures)

        val barData = BarData(yValues1, yValues2)
        barData.setValueFormatter(LargeValueFormatter())

        view.onGetData(true, "", labels, barData)
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

    private fun getFirstDateOfMonth(date: DateTime): Date {
        return DateTime()
                .withYear(date.year)
                .withMonthOfYear(date.monthOfYear)
                .withDayOfMonth(1)
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0)
                .toDate()
    }

    private fun getLastDateOfMonth(date: DateTime): Date {
        return DateTime()
                .withYear(date.year)
                .withMonthOfYear(date.monthOfYear)
                .withDayOfMonth(date.dayOfMonth().maximumValue)
                .withHourOfDay(23)
                .withMinuteOfHour(59)
                .withSecondOfMinute(59)
                .withMillisOfSecond(999)
                .toDate()
    }
}