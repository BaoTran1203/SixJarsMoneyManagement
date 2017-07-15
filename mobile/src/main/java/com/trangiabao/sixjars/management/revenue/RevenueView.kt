package com.trangiabao.sixjars.management.revenue

import com.trangiabao.sixjars.base.model.Revenue

interface RevenueView {
    fun onGetListResult(result: Boolean, msg: String, list: List<Revenue>)
    fun onDeleteResult(result: Boolean, msg: String, position: Int)
}