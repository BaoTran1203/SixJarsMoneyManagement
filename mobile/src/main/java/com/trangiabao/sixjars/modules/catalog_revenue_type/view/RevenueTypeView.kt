package com.trangiabao.sixjars.modules.catalog_revenue_type.view

import com.trangiabao.sixjars.data.model.RevenueType
import com.trangiabao.sixjars.utils.base.BaseView

interface RevenueTypeView : BaseView {
    fun onGetListSuccessed(list: List<RevenueType>)
    fun onUpdateSuccessed(msg: Int, revenueType: RevenueType)
    fun onDeleteSuccessed(msg: Int, position: Int)

    fun onError(msg: Int)
    fun onWarning(msg: Int)
}