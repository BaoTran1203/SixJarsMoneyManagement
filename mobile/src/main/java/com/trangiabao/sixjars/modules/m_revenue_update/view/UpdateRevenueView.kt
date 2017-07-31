package com.trangiabao.sixjars.modules.m_revenue_update.view

import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.data.model.RevenueType
import com.trangiabao.sixjars.utils.base.BaseView

interface UpdateRevenueView : BaseView {
    fun onGetListRevenueTypeSuccessed(list: List<RevenueType>)
    fun onUpdateRevenueSuccessed(msg: Int, revenue: Revenue)
    fun onGetRevenueSuccessed(revenue: Revenue)

    fun onError(msg: Int)
    fun onWarning(msg: Int)
}