package com.trangiabao.sixjars.catalog.expenditure

import com.trangiabao.sixjars.model.ExpenditureType

interface ExpenditureTypeView {

    fun onGetListResult(list: List<ExpenditureType>)

    fun onAddResult(obj: ExpenditureType?)

    fun onUpdateResult(obj: ExpenditureType?)

    fun onDeleteResult(result: Boolean)
}