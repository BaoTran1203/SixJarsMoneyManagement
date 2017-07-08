package com.trangiabao.sixjars.catalog.revenue

import com.trangiabao.sixjars.database.RevenueTypeDB
import com.trangiabao.sixjars.model.RevenueType

class RevenueTypePresenter(private var view: RevenueTypeView) : RevenueTypePresenterImpl {

    override fun getAll() {
        view.onGetListResult(RevenueTypeDB.getAll())
    }

    override fun add(type: RevenueType) {
        view.onAddResult(RevenueTypeDB.add(type))
    }

    override fun update(type: RevenueType) {
        view.onUpdateResult(RevenueTypeDB.update(type))
    }

    override fun delete(id: String) {
        view.onDeleteResult(RevenueTypeDB.delete(id))
    }
}