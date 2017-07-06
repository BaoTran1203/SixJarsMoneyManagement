package com.trangiabao.sixjars.view

import com.trangiabao.sixjars.model.ExpenditureType

interface ExpenditureTypeView {

    fun onGetListResult(list: MutableList<ExpenditureType>)

    fun onGetObjectResult(obj: ExpenditureType?)

    fun onAddResult(obj: ExpenditureType?)

    fun onUpdateResult(obj: ExpenditureType?)

    fun onDeleteResult(result: Boolean)
}