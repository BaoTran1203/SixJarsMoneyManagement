package com.trangiabao.sixjars.catalog.expenditure_type

import com.trangiabao.sixjars.base.model.ExpenditureType

interface ExpenditureTypeView {

    fun onGetListResult(list: List<ExpenditureType>)

    fun onUpdateResult(obj: ExpenditureType?)

    fun onDeleteResult(result: Boolean)
}