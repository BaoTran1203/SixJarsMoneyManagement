package com.trangiabao.sixjars.management.revenue

import com.trangiabao.sixjars.base.model.Revenue

interface RevenueView {

    fun onGetListResult(list: List<Revenue>)

    fun onAddResult(obj: Revenue?)

    fun onUpdateResult(obj: Revenue?)

    fun onDeleteResult(result: Boolean)
}