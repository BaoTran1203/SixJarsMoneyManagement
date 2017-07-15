package com.trangiabao.sixjars.statistical

import org.joda.time.DateTime

interface StatisticalPresenterImpl {
    fun getListRevenue(date: DateTime)
    fun getListExpenditure(date: DateTime)
}