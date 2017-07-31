package com.trangiabao.sixjars.modules.catalog_revenue_type.view

import com.trangiabao.sixjars.data.model.RevenueType
import com.trangiabao.sixjars.utils.base.BaseView

interface RevenueTypeView : BaseView {
    fun onGetListSuccessed(list: List<RevenueType>)
    fun onGetListFailed(msg: Int)

    fun onUpdateSuccessed(msg: Int, revenueType: RevenueType)
    fun onUpdateFailed(msg: Int)

    fun onDeleteSuccessed(msg: Int, position: Int)
    fun onDeleteFailed(msg: Int)
}