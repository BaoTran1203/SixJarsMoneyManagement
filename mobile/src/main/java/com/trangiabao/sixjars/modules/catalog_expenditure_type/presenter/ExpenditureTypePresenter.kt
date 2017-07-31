package com.trangiabao.sixjars.modules.catalog_expenditure_type.presenter

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
        view.onGetListResult(list.isNotEmpty(), "", list)
    }

    override fun update(type: ExpenditureType) {
        val newType = ExpenditureTypeDB.update(type)
        view.onUpdateResult(newType != null, "", newType)
    }

    override fun delete(id: String, position: Int) {
        var result: Boolean = false
        var msg: String
        var pos: Int = -1
        if (ExpenditureTypeDB.isUsed(id)) {
            msg = "is used"
        } else {
            result = ExpenditureTypeDB.delete(id)
            msg = "failed"
            if (result) {
                msg = "success"
                pos = position
            }
        }
        view.onDeleteResult(result, msg, pos)
    }
}