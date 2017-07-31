package com.trangiabao.sixjars.modules.catalog_expenditure_type.view

import com.trangiabao.sixjars.data.model.ExpenditureType
import com.trangiabao.sixjars.utils.base.BaseView

interface ExpenditureTypeView : BaseView {
    fun onGetListResult(result: Boolean, msg: String, list: List<ExpenditureType>)
    fun onUpdateResult(result: Boolean, msg: String, obj: ExpenditureType?)
    fun onDeleteResult(result: Boolean, msg: String, position: Int)
}