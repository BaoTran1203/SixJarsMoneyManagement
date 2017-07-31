package com.trangiabao.sixjars.modules.statistical_bar_chart.view

import com.github.mikephil.charting.data.BarData
import com.trangiabao.sixjars.utils.base.BaseView

interface BarChartView : BaseView {
    fun onGetDataSuccessed(labels: List<String>, barData: BarData)
    fun onError(msg: Int)
    fun onWarning(msg: Int)
}