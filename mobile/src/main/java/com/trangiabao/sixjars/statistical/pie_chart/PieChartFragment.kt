package com.trangiabao.sixjars.statistical.pie_chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.ui.dialog.monthpicker.MonthPickerDialog
import kotlinx.android.synthetic.main.fragment_pie_chart.view.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter

class PieChartFragment : BaseFragment(), PieChartView {

    private var month = DateTime.now()
    private var monthFormat: DateTimeFormatter? = null
    private var _view: View? = null
    private var presenter: PieChartPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _view = inflater!!.inflate(R.layout.fragment_pie_chart, container, false)
        initControls()
        initEvents()
        presenter = PieChartPresenter(this)
        getData()
        return _view
    }

    private fun initControls() {
        _view!!.run {
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
            monthFormat = LocaleHelper.getMonthFormat(context)
            txtMonth.text = monthFormat!!.print(month)
        }
    }

    private fun initEvents() {
        _view!!.run {
            txtMonth.setOnClickListener {
                val dialog = MonthPickerDialog(context, month)
                dialog.setDialogResult(object : MonthPickerDialog.OnDialogResult {
                    override fun onGetTime(date: DateTime) {
                        month = date
                        txtMonth.text = monthFormat!!.print(month)
                        getData()
                    }
                })
                dialog.show()
            }
            radiogroup.setOnCheckedChangeListener { _, _ -> getData() }
            shuffer.setOnClickListener { getData() }
        }
    }

    private fun getData() {
        presenter!!.run {
            if (_view!!.radRevenue.isChecked)
                getListRevenue(month)
            else
                getListExpenditure(month)
        }
    }

    override fun onGetListPieEntryResult(result: Boolean, msg: String, pieData: PieData) {
        _view!!.pieChart.run {
            data = pieData
            highlightValues(null)
            invalidate()
            animateY(500, Easing.EasingOption.EaseInOutQuad)
        }
    }
}
