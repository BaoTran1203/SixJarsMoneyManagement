package com.trangiabao.sixjars.view

import com.trangiabao.sixjars.model.RevenueType

interface RevenueTypeView {
    fun onGetListResult(list: MutableList<RevenueType>)

    fun onGetObjectResult(obj: RevenueType?)

    fun onAddResult(obj: RevenueType?)

    fun onUpdateResult(obj: RevenueType?)

    fun onDeleteResult(result: Boolean)
}