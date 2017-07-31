package com.trangiabao.sixjars.modules.m_revenue.presenter

import com.trangiabao.sixjars.utils.base.BasePresenter
import org.joda.time.DateTime

interface RevenuePresenterImpl : BasePresenter {
    fun filter(from: DateTime, to: DateTime)
    fun delete(id: String, position: Int)
}