package com.trangiabao.sixjars.modules.statistical_bar_chart.presenter

import com.trangiabao.sixjars.utils.base.BasePresenter
import org.joda.time.DateTime

interface BarChartPresenterImpl : BasePresenter {
    fun getData(date: DateTime)
}