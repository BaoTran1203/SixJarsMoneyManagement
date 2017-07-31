package com.trangiabao.sixjars.modules.statistical_pie_chart.presenter

import com.trangiabao.sixjars.utils.base.BasePresenter
import org.joda.time.DateTime

interface PieChartPresenterImpl : BasePresenter {
    fun getData()
    fun getListRevenue(date: DateTime)
    fun getListExpenditure(date: DateTime)
}