package com.trangiabao.sixjars.catalog.expenditure_type

import com.trangiabao.sixjars.base.model.ExpenditureType

interface ExpenditureTypeView {
    fun onGetListResult(result: Boolean, msg: String, list: List<ExpenditureType>)
    fun onUpdateResult(result: Boolean, msg: String, obj: ExpenditureType?)
    fun onDeleteResult(result: Boolean, msg: String, position: Int)
}