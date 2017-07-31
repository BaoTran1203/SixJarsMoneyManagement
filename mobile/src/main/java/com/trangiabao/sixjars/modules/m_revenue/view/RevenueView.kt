package com.trangiabao.sixjars.modules.m_revenue.view

import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.utils.base.BaseView

interface RevenueView : BaseView {
    fun onGetListResult(result: Boolean, msg: String, list: List<Revenue>)
    fun onDeleteResult(result: Boolean, msg: String, position: Int)
}