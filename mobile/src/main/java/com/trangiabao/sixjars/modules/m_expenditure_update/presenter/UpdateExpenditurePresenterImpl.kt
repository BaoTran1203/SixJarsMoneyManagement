package com.trangiabao.sixjars.modules.m_expenditure_update.presenter

import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.utils.base.BasePresenter

interface UpdateExpenditurePresenterImpl : BasePresenter {
    fun getAllJar()
    fun getAllExpenditureType()
    fun updateExpenditure(expenditure: Expenditure)
    fun getExpenditure(id: String)
}