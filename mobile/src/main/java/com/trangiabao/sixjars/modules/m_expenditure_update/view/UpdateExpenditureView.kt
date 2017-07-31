package com.trangiabao.sixjars.modules.m_expenditure_update.view

import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.data.model.ExpenditureType
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.utils.base.BaseView

interface UpdateExpenditureView : BaseView {
    fun onListJarLoaded(result: Boolean, msg: String, list: List<Jar>)
    fun onListExpenditureTypeLoaded(result: Boolean, msg: String, list: List<ExpenditureType>)
    fun onUpdateExpenditureResult(result: Boolean, msg: String, expenditure: Expenditure?)
    fun onGetExpenditure(result: Boolean, msg: String, expenditure: Expenditure?)
}