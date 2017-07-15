package com.trangiabao.sixjars.catalog.expenditure_type

import com.trangiabao.sixjars.base.database.ExpenditureTypeDB
import com.trangiabao.sixjars.base.model.ExpenditureType

class ExpenditureTypePresenter(private var view: ExpenditureTypeView) : ExpenditureTypePresenterImpl {

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