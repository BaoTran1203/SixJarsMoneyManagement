package com.trangiabao.sixjars.modules.m_expenditure.presenter

import com.trangiabao.sixjars.R
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
        val startDate = DateTimeHelper.getStartDate(from)
        val endDate = DateTimeHelper.getEndDate(to)
        val list = ExpenditureDB.find(startDate, endDate)
        if (list == null)
            view.onError(R.string.app_name)
        else if (list.isEmpty())
            view.onWarning(R.string.app_name)
        else
            view.onGetListSuccessed(list)
    }

    override fun delete(id: String, position: Int) {
        if (!ExpenditureDB.delete(id))
            view.onError(R.string.app_name)
        else
            view.onDeleteSuccessed(R.string.app_name, position)
    }
}