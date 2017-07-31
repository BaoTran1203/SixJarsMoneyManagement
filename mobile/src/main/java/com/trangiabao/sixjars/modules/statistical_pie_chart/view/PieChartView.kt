package com.trangiabao.sixjars.modules.statistical_pie_chart.view

import com.github.mikephil.charting.data.PieData
import com.trangiabao.sixjars.utils.base.BaseView

interface PieChartView : BaseView {
    fun onGetListPieEntryResult(result: Boolean, msg: String, pieData: PieData)
}