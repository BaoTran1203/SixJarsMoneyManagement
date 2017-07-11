package com.trangiabao.sixjars.catalog.revenue_type

import com.trangiabao.sixjars.base.model.RevenueType

interface RevenueTypeView {
    fun onGetListResult(result: Boolean, msg: String, list: List<RevenueType>)

    fun onAddResult(result: Boolean, msg: String, obj: RevenueType?)

    fun onUpdateResult(result: Boolean, msg: String, obj: RevenueType?)

    fun onDeleteResult(result: Boolean, msg: String)
}