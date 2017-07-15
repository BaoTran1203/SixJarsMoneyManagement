package com.trangiabao.sixjars.catalog.revenue_type

import com.trangiabao.sixjars.base.database.RevenueTypeDB
import com.trangiabao.sixjars.base.model.RevenueType

class RevenueTypePresenter(private var view: RevenueTypeView) : RevenueTypePresenterImpl {

    override fun getAll() {
        val list = RevenueTypeDB.getAll()
        view.onGetListResult(list.isNotEmpty(), "", list)
    }

    override fun update(type: RevenueType) {
        val newType = RevenueTypeDB.update(type)
        view.onUpdateResult(newType != null, "", newType)
    }

    override fun delete(id: String, position: Int) {
        var result: Boolean = false
        var msg: String
        var pos: Int = -1
        if (RevenueTypeDB.isUsed(id)) {
            msg = "is used"
        } else {
            result = RevenueTypeDB.delete(id)
            msg = "failed"
            if (result) {
                msg = "success"
                pos = position
            }
        }
        view.onDeleteResult(result, msg, pos)
    }
}