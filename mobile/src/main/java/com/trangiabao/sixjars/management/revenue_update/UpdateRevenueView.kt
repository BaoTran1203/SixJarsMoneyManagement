package com.trangiabao.sixjars.management.revenue_update

import com.trangiabao.sixjars.base.model.Revenue
import com.trangiabao.sixjars.base.model.RevenueType

interface UpdateRevenueView {

    fun onListRevenueTypeLoaded(list: List<RevenueType>)

    fun onUpdateRevenueResult(revenue: Revenue?)

    fun onGetRevenue(result: Boolean, msg: String, revenue: Revenue?)
}