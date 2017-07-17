package com.trangiabao.sixjars.statistical.bar_chart

import com.github.mikephil.charting.data.BarData

interface BarChartView {
    fun onGetData(result: Boolean, msg: String, labels: List<String>, barData: BarData)
}