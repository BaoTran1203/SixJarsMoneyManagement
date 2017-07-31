package com.trangiabao.sixjars.modules.catalog_expenditure_type.view

import com.trangiabao.sixjars.data.model.ExpenditureType
import com.trangiabao.sixjars.utils.base.BaseView

interface ExpenditureTypeView : BaseView {
    fun onGetListSuccessed(list: List<ExpenditureType>)
    fun onUpdateSuccessed(msg: Int, expenditureType: ExpenditureType)
    fun onDeleteSuccessed(msg: Int, position: Int)

    fun onError(msg: Int)
    fun onWarning(msg: Int)
}