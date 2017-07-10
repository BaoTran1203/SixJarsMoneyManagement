package com.trangiabao.sixjars.catalog.revenue_type

import com.trangiabao.sixjars.base.model.RevenueType

interface RevenueTypeView {
    fun onGetListResult(list: List<RevenueType>)

    fun onAddResult(obj: RevenueType?)

    fun onUpdateResult(obj: RevenueType?)

    fun onDeleteResult(result: Boolean)
}