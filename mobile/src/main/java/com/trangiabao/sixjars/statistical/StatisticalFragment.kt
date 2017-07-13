package com.trangiabao.sixjars.statistical

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.base.database.RevenueDB
import com.trangiabao.sixjars.base.ui.dialog.monthpicker.MonthPickerDialog
import kotlinx.android.synthetic.main.fragment_statistical.view.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import java.util.*

class StatisticalFragment : BaseFragment() {

    private var month = DateTime.now()
    private var monthFormat: DateTimeFormatter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_statistical, container, false)
        initControls(view)
        initEvents(view)
        return view
    }

    private fun initControls(view: View) {
        view.run {
            pieChart.setUsePercentValues(true)
            pieChart.description.isEnabled = false
            pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
            pieChart.dragDecelerationFrictionCoef = 0.95f
            pieChart.isDrawHoleEnabled = true
            pieChart.holeRadius = 58f
            pieChart.transparentCircleRadius = 61f
            pieChart.setHoleColor(Color.TRANSPARENT)
            pieChart.rotationAngle = 0f
            pieChart.isRotationEnabled = true
            pieChart.isHighlightPerTapEnabled = true
            pieChart.setEntryLabelTextSize(12f)
            pieChart.setEntryLabelColor(Color.BLACK)
            pieChart.legend.run {
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                orientation = Legend.LegendOrientation.VERTICAL
                setDrawInside(false)
                xEntrySpace = 7f
                yEntrySpace = 0f
                yOffset = 0f
                isWordWrapEnabled = true
            }
        }
        getData(view)
        monthFormat = LocaleHelper.getMonthFormat(context)
        view.txtMonth.text = monthFormat!!.print(month)
    }

    private fun initEvents(view: View) {
        view.txtMonth.setOnClickListener {
            val dialog = MonthPickerDialog(context, month)
            dialog.setDialogResult(object : MonthPickerDialog.OnDialogResult {
                override fun onGetTime(date: DateTime) {
                    this@StatisticalFragment.month = date
                    view.txtMonth.text = monthFormat!!.print(this@StatisticalFragment.month)
                    //presenter!!.filter(dateFrom, dateTo)
                }
            })
            dialog.show()
        }
    }

    private fun getData(view: View) {
        val entries: MutableList<PieEntry> = getPieEntry()
        val dataSet = getPieDataSet(entries, "")
        view.pieChart.data = getPieData(dataSet)
        view.pieChart.highlightValues(null)
        view.pieChart.invalidate()
        view.pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad)
    }

    private fun getPieEntry(): MutableList<PieEntry> {
        val list = RevenueDB.getAll()
        val map = list.map { it.revenueType!!.type to it.amount }.toMap()
        val entries: MutableList<PieEntry> = mutableListOf()
        for ((key, value) in map) {
            entries.add(PieEntry(value!!.toFloat(), key))
        }
        entries.sortBy { x -> x.value }
        return shuffle(entries)
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
