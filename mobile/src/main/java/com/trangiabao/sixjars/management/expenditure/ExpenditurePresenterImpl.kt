package com.trangiabao.sixjars.management.expenditure

import org.joda.time.DateTime

interface ExpenditurePresenterImpl {
    fun filter(from: DateTime, to: DateTime)
    fun delete(id: String, position: Int)
}