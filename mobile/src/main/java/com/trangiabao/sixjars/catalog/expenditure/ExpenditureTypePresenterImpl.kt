package com.trangiabao.sixjars.catalog.expenditure

import com.trangiabao.sixjars.model.ExpenditureType

interface ExpenditureTypePresenterImpl {

    fun getAll()

    fun add(type: ExpenditureType)

    fun update(type: ExpenditureType)

    fun delete(id: String)
}