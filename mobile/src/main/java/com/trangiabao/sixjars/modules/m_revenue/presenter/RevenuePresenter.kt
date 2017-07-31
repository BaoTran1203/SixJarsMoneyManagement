package com.trangiabao.sixjars.modules.m_revenue.presenter

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.database.RevenueDB
import com.trangiabao.sixjars.modules.m_revenue.view.RevenueView
import com.trangiabao.sixjars.utils.helper.DateTimeHelper
import org.joda.time.DateTime

class RevenuePresenter(private var view: RevenueView) : RevenuePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun filter(from: DateTime, to: DateTime) {
        val startDate = DateTimeHelper.getStartDate(from)
        val endDate = DateTimeHelper.getEndDate(to)
        val list = RevenueDB.find(startDate, endDate)
        if (list == null)
            view.onError(R.string.app_name)
        else if (list.isEmpty())
            view.onWarning(R.string.app_name)
        else
            view.onGetListSuccessed(list)
    }

    override fun delete(id: String, position: Int) {
        if (!RevenueDB.delete(id))
            view.onError(R.string.app_name)
        else
            view.onDeleteSuccessed(R.string.app_name, position)
    }
}