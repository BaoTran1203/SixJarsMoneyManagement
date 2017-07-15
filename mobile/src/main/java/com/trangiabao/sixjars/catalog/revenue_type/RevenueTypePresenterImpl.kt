package com.trangiabao.sixjars.catalog.revenue_type

import com.trangiabao.sixjars.base.model.RevenueType

interface RevenueTypePresenterImpl {
    fun getAll()
    fun update(type: RevenueType)
    fun delete(id: String, position: Int)
}