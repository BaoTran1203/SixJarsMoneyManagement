package com.trangiabao.sixjars.management.edit_revenue

import com.trangiabao.sixjars.base.database.RevenueDB
import com.trangiabao.sixjars.base.database.RevenueTypeDB
import com.trangiabao.sixjars.base.model.Revenue

class EditRevenuePresenter(private var view: EditRevenueView) : EditRevenuePresenterImpl {

    override fun getAllRevenueType() {
        view.onGetListRevenueTypeResult(RevenueTypeDB.getAll())
    }

    override fun updateRevenue(revenue: Revenue) {
        view.onEditRevenueResult(RevenueDB.update(revenue))
    }

    override fun getRevenue(id: String) {
        view.onGetRevenueResult(RevenueDB.find(id))
    }
}