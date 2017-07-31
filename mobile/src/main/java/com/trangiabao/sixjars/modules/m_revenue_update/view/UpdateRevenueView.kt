package com.trangiabao.sixjars.modules.m_revenue_update.view

import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.data.model.RevenueType
import com.trangiabao.sixjars.utils.base.BaseView

interface UpdateRevenueView : BaseView {
    fun onListRevenueTypeLoaded(result: Boolean, msg: String, list: List<RevenueType>)
    fun onUpdateRevenueResult(result: Boolean, msg: String, revenue: Revenue?)
    fun onGetRevenue(result: Boolean, msg: String, revenue: Revenue?)
}