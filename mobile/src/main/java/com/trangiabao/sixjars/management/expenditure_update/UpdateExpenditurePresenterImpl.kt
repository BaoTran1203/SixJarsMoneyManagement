package com.trangiabao.sixjars.management.expenditure_update

import com.trangiabao.sixjars.base.model.Expenditure

interface UpdateExpenditurePresenterImpl {
    fun getAllJar()
    fun getAllExpenditureType()
    fun updateExpenditure(expenditure: Expenditure)
    fun getExpenditure(id: String)
}