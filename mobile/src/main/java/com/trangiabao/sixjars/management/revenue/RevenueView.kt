package com.trangiabao.sixjars.management.revenue

import com.trangiabao.sixjars.model.Revenue

interface RevenueView {

    fun onGetListResult(list: MutableList<Revenue>)

    fun onAddResult(obj: Revenue?)

    fun onUpdateResult(obj: Revenue?)

    fun onDeleteResult(result: Boolean)
}