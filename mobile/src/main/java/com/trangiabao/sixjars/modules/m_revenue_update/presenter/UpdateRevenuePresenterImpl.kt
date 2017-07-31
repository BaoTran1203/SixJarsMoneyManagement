package com.trangiabao.sixjars.modules.m_revenue_update.presenter

import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.utils.base.BasePresenter

interface UpdateRevenuePresenterImpl : BasePresenter {
    fun getAllRevenueType()
    fun updateRevenue(revenue: Revenue)
    fun getRevenue(id: String)
}