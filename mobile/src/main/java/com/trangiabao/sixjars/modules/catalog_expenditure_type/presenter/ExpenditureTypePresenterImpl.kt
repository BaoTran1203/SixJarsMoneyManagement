package com.trangiabao.sixjars.modules.catalog_expenditure_type.presenter

import com.trangiabao.sixjars.data.model.ExpenditureType
import com.trangiabao.sixjars.utils.base.BasePresenter

interface ExpenditureTypePresenterImpl : BasePresenter {
    fun getAll()
    fun update(type: ExpenditureType)
    fun delete(id: String, position: Int)
}