package com.trangiabao.sixjars.management.add_revenue

import com.trangiabao.sixjars.base.model.Revenue

interface AddRevenuePresenterImpl {

    fun getAllRevenueType()

    fun addRevenue(revenue: Revenue)
}