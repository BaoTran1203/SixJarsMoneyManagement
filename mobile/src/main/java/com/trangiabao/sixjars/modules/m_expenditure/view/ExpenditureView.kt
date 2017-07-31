package com.trangiabao.sixjars.modules.m_expenditure.view

import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.utils.base.BaseView

interface ExpenditureView : BaseView {
    fun onGetListResult(result: Boolean, msg: String, list: List<Expenditure>)
    fun onDeleteResult(result: Boolean, msg: String, position: Int)
}