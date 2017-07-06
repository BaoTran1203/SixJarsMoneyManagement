package com.trangiabao.sixjars.presenter

import com.trangiabao.sixjars.model.RevenueType

interface RevenueTypePresenterImpl {

    fun getAll()

    fun get(id: String)

    fun add(type: RevenueType)

    fun update(type: RevenueType)

    fun delete(id: String)
}