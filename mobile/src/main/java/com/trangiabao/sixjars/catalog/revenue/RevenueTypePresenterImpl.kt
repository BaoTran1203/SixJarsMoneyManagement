package com.trangiabao.sixjars.catalog.revenue

import com.trangiabao.sixjars.model.RevenueType

interface RevenueTypePresenterImpl {

    fun getAll()

    fun add(type: RevenueType)

    fun update(type: RevenueType)

    fun delete(id: String)
}