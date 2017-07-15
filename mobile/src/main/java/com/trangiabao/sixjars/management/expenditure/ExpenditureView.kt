package com.trangiabao.sixjars.management.expenditure

import com.trangiabao.sixjars.base.model.Expenditure

interface ExpenditureView {
    fun onGetListResult(result: Boolean, msg: String, list: List<Expenditure>)
    fun onDeleteResult(result: Boolean, msg: String, position: Int)
}