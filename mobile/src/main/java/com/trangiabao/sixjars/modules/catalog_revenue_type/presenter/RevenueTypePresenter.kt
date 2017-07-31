package com.trangiabao.sixjars.modules.catalog_revenue_type.presenter

import com.trangiabao.sixjars.R
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
        if (list.isEmpty())
            view.onGetListFailed(R.string.app_name)
        else
            view.onGetListSuccessed(list)
    }

    override fun update(type: RevenueType) {
        val newType = RevenueTypeDB.update(type)
        if (newType == null)
            view.onUpdateFailed(R.string.app_name)
        else
            view.onUpdateSuccessed(R.string.app_name, newType)
    }

    override fun delete(id: String, position: Int) {
        if (RevenueTypeDB.isUsed(id)) {
            view.onDeleteWrong(R.string.app_name)
        } else {
            val result = RevenueTypeDB.delete(id)
            if (!result) {
                view.onDeleteFailed(R.string.app_name)
            } else {
                view.onDeleteSuccessed(R.string.app_name, position)
            }
        }
    }
}