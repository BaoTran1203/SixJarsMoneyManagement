package com.trangiabao.sixjars.statistical.pie_chart

import org.joda.time.DateTime

interface PieChartPresenterImpl {
    fun getListRevenue(date: DateTime)
    fun getListExpenditure(date: DateTime)
}