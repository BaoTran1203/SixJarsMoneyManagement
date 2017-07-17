package com.trangiabao.sixjars.statistical.pie_chart

import com.github.mikephil.charting.data.PieData

interface PieChartView {
    fun onGetListPieEntryResult(result: Boolean, msg: String, pieData: PieData)
}