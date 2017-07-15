package com.trangiabao.sixjars.management.revenue_update

import com.trangiabao.sixjars.base.database.RevenueDB
import com.trangiabao.sixjars.base.database.RevenueTypeDB
import com.trangiabao.sixjars.base.model.Revenue

class UpdateRevenuePresenter(private var view: UpdateRevenueView) : UpdateRevenuePresenterImpl {

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