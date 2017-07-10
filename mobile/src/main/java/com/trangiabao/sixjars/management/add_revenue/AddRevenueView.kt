package com.trangiabao.sixjars.management.add_revenue

import com.trangiabao.sixjars.base.model.Revenue
import com.trangiabao.sixjars.base.model.RevenueType

interface AddRevenueView {

    fun onListRevenueTypeLoaded(list: List<RevenueType>)

    fun onAddRevenueResult(revenue: Revenue?)
}