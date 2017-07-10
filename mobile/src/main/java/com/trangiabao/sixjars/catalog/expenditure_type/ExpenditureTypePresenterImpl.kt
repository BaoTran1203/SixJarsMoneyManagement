package com.trangiabao.sixjars.catalog.expenditure_type

import com.trangiabao.sixjars.base.model.ExpenditureType

interface ExpenditureTypePresenterImpl {

    fun getAll()

    fun add(type: ExpenditureType)

    fun update(type: ExpenditureType)

    fun delete(id: String)
}