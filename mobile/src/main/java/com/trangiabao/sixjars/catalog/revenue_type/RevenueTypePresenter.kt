package com.trangiabao.sixjars.catalog.revenue_type

import com.trangiabao.sixjars.base.database.RevenueTypeDB
import com.trangiabao.sixjars.base.model.RevenueType

class RevenueTypePresenter(private var view: RevenueTypeView) : RevenueTypePresenterImpl {

    override fun getAll() {
        val list = RevenueTypeDB.getAll()
        view.onGetListResult(list.isNotEmpty(), "", list)
    }

    override fun add(type: RevenueType) {
        val newType = RevenueTypeDB.add(type)
        view.onAddResult(newType != null, "", newType)
    }

    override fun update(type: RevenueType) {
        val newType = RevenueTypeDB.update(type)
        view.onUpdateResult(newType != null, "", newType)
    }

    override fun delete(id: String) {
        if (RevenueTypeDB.isUsed(id))
            view.onDeleteResult(false, "isUsed")
        else
            view.onDeleteResult(RevenueTypeDB.delete(id), "")
    }
}