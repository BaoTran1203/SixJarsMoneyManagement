package com.trangiabao.sixjars.modules.m_expenditure.presenter

import com.trangiabao.sixjars.data.database.ExpenditureDB
import com.trangiabao.sixjars.modules.m_expenditure.view.ExpenditureView
import com.trangiabao.sixjars.utils.helper.DateTimeHelper
import org.joda.time.DateTime

class ExpenditurePresenter(private var view: ExpenditureView) : ExpenditurePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun filter(from: DateTime, to: DateTime) {
        val list = ExpenditureDB.find(DateTimeHelper.getStartDate(from), DateTimeHelper.getEndDate(to))
        view.onGetListResult(list.isNotEmpty(), "", list)
    }

    override fun delete(id: String, position: Int) {
        val result = ExpenditureDB.delete(id)
        val msg = if (result) "Item has been removed" else "Failed"
        view.onDeleteResult(result, msg, position)
    }
}