package com.trangiabao.sixjars.statistical

import com.github.mikephil.charting.data.PieEntry

interface StatisticalView {
    fun onGetListPieEntryResult(result: Boolean, msg: String, entries: MutableList<PieEntry>)
}