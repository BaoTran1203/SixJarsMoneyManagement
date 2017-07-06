package com.trangiabao.sixjars.presenter

import com.trangiabao.sixjars.model.ExpenditureType

interface ExpenditureTypePresenterImpl {

    fun getAll()

    fun get(id: String)

    fun add(type: ExpenditureType)

    fun update(type: ExpenditureType)

    fun delete(id: String)
}