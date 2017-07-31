package com.trangiabao.sixjars.modules.statistical_pie_chart.presenter

import android.graphics.Color
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.database.ExpenditureDB
import com.trangiabao.sixjars.data.database.RevenueDB
import com.trangiabao.sixjars.modules.statistical_pie_chart.view.PieChartView
import com.trangiabao.sixjars.utils.helper.DateTimeHelper
import org.joda.time.DateTime
import java.util.*

class PieChartPresenter(private var view: PieChartView) : PieChartPresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun getData() {
        view.onGetDataFunction()
    }

    override fun getListRevenue(date: DateTime) {
        val firstDate = DateTimeHelper.getFirstDateOfMonth(date)
        val lastDate = DateTimeHelper.getLastDateOfMonth(date)
        val list = RevenueDB.find(firstDate, lastDate)

        if (list == null)
            view.onGetDataFailed(R.string.app_name)
        else if (list.isEmpty())
            view.onGetEmptyData(R.string.app_name)
        else {
            val map = list.map { it.revenueType!!.type to it.amount }.toMap()
            val entries: MutableList<PieEntry> = mutableListOf()
            for ((key, value) in map) {
                entries.add(PieEntry(value!!.toFloat(), key))
            }

            val dataSet = getPieDataSet(shuffle(entries), "")
            val data = getPieData(dataSet)
            view.onGetDataSuccessed(data)
        }
    }

    override fun getListExpenditure(date: DateTime) {
        val firstDate = DateTimeHelper.getFirstDateOfMonth(date)
        val lastDate = DateTimeHelper.getLastDateOfMonth(date)
        val list = ExpenditureDB.find(firstDate, lastDate)

        if (list == null)
            view.onGetDataFailed(R.string.app_name)
        else if (list.isEmpty())
            view.onGetEmptyData(R.string.app_name)
        else {
            val map = list.map { it.expenditureType!!.type to it.amount }.toMap()
            val entries: MutableList<PieEntry> = mutableListOf()
            for ((key, value) in map) {
                entries.add(PieEntry(value!!.toFloat(), key))
            }

            val dataSet = getPieDataSet(shuffle(entries), "")
            val data = getPieData(dataSet)
            view.onGetDataSuccessed(data)
        }
    }

    private fun shuffle(items: MutableList<PieEntry>): MutableList<PieEntry> {
        val rg: Random = Random()
        for (i in 0..items.size - 1) {
            val randomPosition = rg.nextInt(items.size)
            val tmp: PieEntry = items[i]
            items[i] = items[randomPosition]
            items[randomPosition] = tmp
        }
        return items
    }

    private fun getPieDataSet(entries: MutableList<PieEntry>, label: String): PieDataSet {
        val dataSet = PieDataSet(entries, label)
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f
        dataSet.colors = getColors()
        return dataSet
    }

    private fun getColors(): MutableList<Int> {
        val colors: MutableList<Int> = mutableListOf()
        colors += ColorTemplate.VORDIPLOM_COLORS.toMutableList()
        colors += ColorTemplate.JOYFUL_COLORS.toMutableList()
        colors += ColorTemplate.COLORFUL_COLORS.toMutableList()
        colors += ColorTemplate.LIBERTY_COLORS.toMutableList()
        colors += ColorTemplate.PASTEL_COLORS.toMutableList()
        colors += ColorTemplate.MATERIAL_COLORS.toMutableList()
        colors.add(ColorTemplate.getHoloBlue())
        return colors
    }

    private fun getPieData(dataSet: PieDataSet): PieData {
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        return data
    }
}