package com.trangiabao.sixjars.management.expenditure_update

import com.trangiabao.sixjars.base.model.Expenditure
import com.trangiabao.sixjars.base.model.ExpenditureType
import com.trangiabao.sixjars.base.model.Jar

interface UpdateExpenditureView {
    fun onListJarLoaded(result: Boolean, msg: String, list: List<Jar>)
    fun onListExpenditureTypeLoaded(result: Boolean, msg: String, list: List<ExpenditureType>)
    fun onUpdateExpenditureResult(result: Boolean, msg: String, expenditure: Expenditure?)
    fun onGetExpenditure(result: Boolean, msg: String, expenditure: Expenditure?)
}