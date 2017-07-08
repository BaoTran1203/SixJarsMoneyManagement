package com.trangiabao.sixjars.catalog.revenue

import com.trangiabao.sixjars.model.RevenueType

interface RevenueTypeView {
    fun onGetListResult(list: List<RevenueType>)

    fun onAddResult(obj: RevenueType?)

    fun onUpdateResult(obj: RevenueType?)

    fun onDeleteResult(result: Boolean)
}