package com.trangiabao.sixjars.modules.statistical_pie_chart.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.modules.statistical_pie_chart.presenter.PieChartPresenter
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.dialog.monthpicker.MonthPickerDialog
import com.trangiabao.sixjars.utils.helper.DateTimeHelper
import com.trangiabao.sixjars.utils.helper.ToastHelper
import kotlinx.android.synthetic.main.fragment_pie_chart.view.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter

class PieChartFragment : BaseFragment(), PieChartView {

    private var month = DateTime.now()
    private var monthFormat: DateTimeFormatter? = null
    private var mView: View? = null
    private var presenter: PieChartPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_pie_chart, container, false)
        presenter = PieChartPresenter(this)
        presenter!!.createView()
        presenter!!.getData()
        return mView
    }

    override fun onInitControls() {
        mView!!.run {
            pieChart.run {
                setUsePercentValues(true)
                description.isEnabled = false
                setExtraOffsets(5f, 10f, 5f, 5f)
                dragDecelerationFrictionCoef = 0.95f
                isDrawHoleEnabled = true
                holeRadius = 58f
                transparentCircleRadius = 61f
                setHoleColor(Color.TRANSPARENT)
                rotationAngle = 0f
                isRotationEnabled = true
                isHighlightPerTapEnabled = true
                setEntryLabelTextSize(12f)
                setEntryLabelColor(Color.BLACK)
                setDrawEntryLabels(false)
                legend.run {
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
            monthFormat = DateTimeHelper.getMonthFormat(context)
            txtMonth.text = monthFormat!!.print(month)
        }
    }

    override fun onInitEvents() {
        mView!!.run {
            txtMonth.setOnClickListener {
                val dialog = MonthPickerDialog(context, month)
                dialog.setDialogResult(object : MonthPickerDialog.OnDialogResult {
                    override fun onGetTime(date: DateTime) {
                        month = date
                        txtMonth.text = monthFormat!!.print(month)
                        presenter!!.getData()
                    }
                })
                dialog.show()
            }
            radiogroup.setOnCheckedChangeListener { _, _ -> presenter!!.getData() }
            shuffer.setOnClickListener { presenter!!.getData() }
        }
    }

    override fun onGetDataFunction() {
        presenter!!.run {
            if (mView!!.radRevenue.isChecked)
                getListRevenue(month)
            else
                getListExpenditure(month)
        }
    }

    override fun onGetDataSuccessed(pieData: PieData) {
        mView!!.pieChart.run {
            data = pieData
            highlightValues(null)
            invalidate()
            animateY(500, Easing.EasingOption.EaseInOutQuad)
        }
    }

    override fun onGetDataFailed(msg: Int) {
        ToastHelper.toastError(context, msg)
    }

    override fun onGetEmptyData(msg: Int) {
        ToastHelper.toastWarning(context, msg)
    }
}
