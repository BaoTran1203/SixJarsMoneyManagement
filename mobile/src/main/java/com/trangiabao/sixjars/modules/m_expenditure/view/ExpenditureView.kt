package com.trangiabao.sixjars.modules.m_expenditure.view

import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.utils.base.BaseView

interface ExpenditureView : BaseView {
    fun onGetListSuccessed(list: List<Expenditure>)
    fun onDeleteSuccessed(msg: Int, position: Int)

    fun onError(msg: Int)
    fun onWarning(msg: Int)
}