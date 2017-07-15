package com.trangiabao.sixjars.management.revenue_update

import com.trangiabao.sixjars.base.model.Revenue
import com.trangiabao.sixjars.base.model.RevenueType

interface UpdateRevenueView {
    fun onListRevenueTypeLoaded(result: Boolean, msg: String, list: List<RevenueType>)
    fun onUpdateRevenueResult(result: Boolean, msg: String, revenue: Revenue?)
    fun onGetRevenue(result: Boolean, msg: String, revenue: Revenue?)
}