package com.trangiabao.sixjars.modules.m_revenue_update.presenter

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.database.RevenueDB
import com.trangiabao.sixjars.data.database.RevenueTypeDB
import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.modules.m_revenue_update.view.UpdateRevenueView

class UpdateRevenuePresenter(private var view: UpdateRevenueView) : UpdateRevenuePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun getAllRevenueType() {
        val list = RevenueTypeDB.getAll()
        if (list.isEmpty())
            view.onError(R.string.app_name)
        else
            view.onGetListRevenueTypeSuccessed(list)
    }

    override fun updateRevenue(revenue: Revenue) {
        if (revenue.amount!! < 1.0)
            view.onWarning(R.string.app_name)
        else if (revenue.revenueType == null)
            view.onWarning(R.string.app_name)
        else {
            val newRevenue = RevenueDB.update(revenue)
            if (newRevenue == null)
                view.onError(R.string.app_name)
            else
                view.onUpdateRevenueSuccessed(R.string.app_name, newRevenue)
        }
    }

    override fun getRevenue(id: String) {
        val revenue: Revenue? = RevenueDB.find(id)
        if (revenue == null)
            view.onError(R.string.app_name)
        else
            view.onGetRevenueSuccessed(revenue)
    }
}