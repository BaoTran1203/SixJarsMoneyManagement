package com.trangiabao.sixjars.modules.m_expenditure.presenter

import com.trangiabao.sixjars.utils.base.BasePresenter
import org.joda.time.DateTime

interface ExpenditurePresenterImpl : BasePresenter {
    fun filter(from: DateTime, to: DateTime)
    fun delete(id: String, position: Int)
}