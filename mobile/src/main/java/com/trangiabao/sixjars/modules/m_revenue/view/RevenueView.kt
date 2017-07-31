package com.trangiabao.sixjars.modules.m_revenue.view

import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.utils.base.BaseView

interface RevenueView : BaseView {
    fun onGetListSuccessed(list: List<Revenue>)
    fun onDeleteSuccessed(msg: Int, position: Int)

    fun onError(msg: Int)
    fun onWarning(msg: Int)
}