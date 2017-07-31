package com.trangiabao.sixjars.modules.m_revenue_update.presenter

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
        view.onListRevenueTypeLoaded(list.isNotEmpty(), "", list)
    }

    override fun updateRevenue(revenue: Revenue) {
        val newRevenue = RevenueDB.update(revenue)
        view.onUpdateRevenueResult(newRevenue != null, "", newRevenue)
    }

    override fun getRevenue(id: String) {
        if (id == "")
            view.onGetRevenue(true, "Add", null)
        else {
            val obj: Revenue? = RevenueDB.find(id)
            view.onGetRevenue(obj == null, "Edit", obj)
        }
    }
}