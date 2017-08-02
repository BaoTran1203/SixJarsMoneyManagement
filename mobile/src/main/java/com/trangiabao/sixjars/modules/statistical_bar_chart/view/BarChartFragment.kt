package com.trangiabao.sixjars.modules.statistical_bar_chart.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.modules.statistical_bar_chart.presenter.BarChartPresenter
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.dialog.monthpicker.MonthPickerDialog
import com.trangiabao.sixjars.utils.helper.DateTimeHelper
import com.trangiabao.sixjars.utils.helper.ToastHelper
import kotlinx.android.synthetic.main.fragment_bar_chart.view.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter

class BarChartFragment : BaseFragment(), BarChartView {

    private val BAR_WIDTH: Float = 0.3f
    private val BAR_SPACE: Float = 0f
    private val GROUP_SPACE: Float = 0.4f
    private val GROUP_COUNT: Int = 6

    private var mView: View? = null
    private var month = DateTime.now()
    private var monthFormat: DateTimeFormatter? = null
    private var presenter: BarChartPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_bar_chart, container, false)
        presenter = BarChartPresenter(this)
        presenter!!.createView()
        presenter!!.getData(month)
        return mView
    }

    override fun onInitControls() {
        mView!!.barChart.run {
            description = null
            setScaleEnabled(false)
            setTouchEnabled(false)
            setPinchZoom(false)
            setDrawBarShadow(false)
            setDrawGridBackground(false)

            legend.run {
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                orientation = Legend.LegendOrientation.VERTICAL
                setDrawInside(true)
                yOffset = 0f
                xOffset = 10f
                yEntrySpace = 0f
                textSize = 8f
            }

            xAxis.run {
                granularity = 1f
                isGranularityEnabled = true
                setCenterAxisLabels(true)
                setDrawGridLines(false)
                axisMaximum = 6F
                position = XAxis.XAxisPosition.BOTTOM
                valueFormatter = IAxisValueFormatter { value, _ ->
                    value.toInt().toString()
                }
            }

            axisRight.isEnabled = false
            axisLeft.run {
                valueFormatter = LargeValueFormatter()
                setDrawGridLines(true)
                spaceTop = 35f
                axisMinimum = 0f
            }
        }
        monthFormat = DateTimeHelper.getMonthFormat(context)
        mView!!.txtMonth.text = monthFormat!!.print(month)
    }

    override fun onInitEvents() {
        mView!!.run {
            txtMonth.setOnClickListener {
                val dialog = MonthPickerDialog(context, month)
                dialog.setDialogResult(object : MonthPickerDialog.OnDialogResult {
                    override fun onGetTime(date: DateTime) {
                        month = date
                        txtMonth.text = monthFormat!!.print(month)
                        presenter!!.getData(month)
                    }
                })
                dialog.show()
            }
        }
    }

    override fun onGetDataSuccessed(labels: List<String>, barData: BarData) {
        mView!!.barChart.run {
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            data = barData
            barData.barWidth = BAR_WIDTH
            barData.getDataSetByIndex(0).label = "Thu"
            barData.getDataSetByIndex(1).label = "Chi"
            xAxis.axisMinimum = 0F
            xAxis.axisMaximum = barData.getGroupWidth(GROUP_SPACE, BAR_SPACE) * GROUP_COUNT
            groupBars(0F, GROUP_SPACE, BAR_SPACE)
            invalidate()
            animateY(800, Easing.EasingOption.EaseInOutQuad)
        }
    }

    override fun onGetDataFailed(msg: Int) {
        ToastHelper.toastError(context, msg)
    }

    override fun onGetEmptyData(msg: Int) {
        mView!!.barChart.setNoDataText(getString(msg))
    }
}
