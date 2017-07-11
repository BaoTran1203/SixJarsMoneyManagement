package com.trangiabao.sixjars.management.revenue_update

import com.trangiabao.sixjars.base.database.RevenueDB
import com.trangiabao.sixjars.base.database.RevenueTypeDB
import com.trangiabao.sixjars.base.model.Revenue

class UpdateRevenuePresenter(private var view: UpdateRevenueView) : UpdateRevenuePresenterImpl {

    override fun getAllRevenueType() {
        view.onListRevenueTypeLoaded(RevenueTypeDB.getAll())
    }

    override fun updateRevenue(revenue: Revenue) {
        view.onUpdateRevenueResult(RevenueDB.update(revenue))
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