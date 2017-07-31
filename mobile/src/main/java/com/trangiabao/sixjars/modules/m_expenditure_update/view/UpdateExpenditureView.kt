package com.trangiabao.sixjars.modules.m_expenditure_update.view

import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.data.model.ExpenditureType
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.utils.base.BaseView

interface UpdateExpenditureView : BaseView {
    fun onGetListJarSuccessed(list: List<Jar>)
    fun onGetListExpenditureTypeSuccessed(list: List<ExpenditureType>)
    fun onUpdateExpenditureSuccessed(msg: Int, expenditure: Expenditure)
    fun onGetExpenditureSuccessed(expenditure: Expenditure)

    fun onError(msg: Int)
    fun onWarning(msg: Int)
}