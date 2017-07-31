package com.trangiabao.sixjars.modules.catalog_revenue_type.presenter

import com.trangiabao.sixjars.data.database.RevenueTypeDB
import com.trangiabao.sixjars.data.model.RevenueType
import com.trangiabao.sixjars.modules.catalog_revenue_type.view.RevenueTypeView

class RevenueTypePresenter(private var view: RevenueTypeView) : RevenueTypePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

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