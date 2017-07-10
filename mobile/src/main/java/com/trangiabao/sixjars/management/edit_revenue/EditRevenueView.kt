package com.trangiabao.sixjars.management.edit_revenue

import com.trangiabao.sixjars.base.model.Revenue
import com.trangiabao.sixjars.base.model.RevenueType

interface EditRevenueView {

    fun onGetListRevenueTypeResult(list: List<RevenueType>)

    fun onEditRevenueResult(revenue: Revenue?)

    fun onGetRevenueResult(revenue: Revenue?)
}