package com.trangiabao.sixjars.management.edit_revenue

import com.trangiabao.sixjars.base.model.Revenue

interface EditRevenuePresenterImpl {

    fun getAllRevenueType()

    fun updateRevenue(revenue: Revenue)

    fun getRevenue(id: String)
}