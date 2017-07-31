package com.trangiabao.sixjars.modules.catalog_revenue_type.view

import com.trangiabao.sixjars.data.model.RevenueType
import com.trangiabao.sixjars.utils.base.BaseView

interface RevenueTypeView : BaseView {
    fun onGetListResult(result: Boolean, msg: String, list: List<RevenueType>)
    fun onUpdateResult(result: Boolean, msg: String, obj: RevenueType?)
    fun onDeleteResult(result: Boolean, msg: String, position: Int)
}