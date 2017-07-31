package com.trangiabao.sixjars.modules.catalog_revenue_type.presenter

import com.trangiabao.sixjars.data.model.RevenueType
import com.trangiabao.sixjars.utils.base.BasePresenter

interface RevenueTypePresenterImpl : BasePresenter {
    fun getAll()
    fun update(type: RevenueType)
    fun delete(id: String, position: Int)
}