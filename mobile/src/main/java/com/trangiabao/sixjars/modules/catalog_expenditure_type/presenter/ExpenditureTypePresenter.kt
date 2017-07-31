package com.trangiabao.sixjars.modules.catalog_expenditure_type.presenter

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.database.ExpenditureTypeDB
import com.trangiabao.sixjars.data.model.ExpenditureType
import com.trangiabao.sixjars.modules.catalog_expenditure_type.view.ExpenditureTypeView

class ExpenditureTypePresenter(private var view: ExpenditureTypeView) : ExpenditureTypePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun getAll() {
        val list = ExpenditureTypeDB.getAll()
        if (list.isEmpty())
            view.onError(R.string.app_name)
        else
            view.onGetListSuccessed(list)
    }

    override fun update(type: ExpenditureType) {
        val newType = ExpenditureTypeDB.update(type)
        if (newType == null)
            view.onError(R.string.app_name)
        else
            view.onUpdateSuccessed(R.string.app_name, newType)
    }

    override fun delete(id: String, position: Int) {
        if (ExpenditureTypeDB.isUsed(id)) {
            view.onWarning(R.string.app_name)
        } else {
            val result = ExpenditureTypeDB.delete(id)
            if (!result) {
                view.onError(R.string.app_name)
            } else {
                view.onDeleteSuccessed(R.string.app_name, position)
            }
        }
    }
}