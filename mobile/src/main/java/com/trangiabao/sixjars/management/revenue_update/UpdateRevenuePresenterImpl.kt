package com.trangiabao.sixjars.management.revenue_update

import com.trangiabao.sixjars.base.model.Revenue

interface UpdateRevenuePresenterImpl {

    fun getAllRevenueType()

    fun updateRevenue(revenue: Revenue)

    fun getRevenue(id: String)
}