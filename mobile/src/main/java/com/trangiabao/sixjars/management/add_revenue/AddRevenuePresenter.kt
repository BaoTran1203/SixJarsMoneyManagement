package com.trangiabao.sixjars.management.add_revenue

import com.trangiabao.sixjars.base.database.RevenueDB
import com.trangiabao.sixjars.base.database.RevenueTypeDB
import com.trangiabao.sixjars.base.model.Revenue

class AddRevenuePresenter(private var view: AddRevenueView) : AddRevenuePresenterImpl {

    override fun getAllRevenueType() {
        view.onListRevenueTypeLoaded(RevenueTypeDB.getAll())
    }

    override fun addRevenue(revenue: Revenue) {
        view.onAddRevenueResult(RevenueDB.add(revenue))
    }

}