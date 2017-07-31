package com.trangiabao.sixjars.modules.statistical_bar_chart.view

import com.github.mikephil.charting.data.BarData
import com.trangiabao.sixjars.utils.base.BaseView

interface BarChartView : BaseView {
    fun onGetData(result: Boolean, msg: String, labels: List<String>, barData: BarData)
}