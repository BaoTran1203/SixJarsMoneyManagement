package com.trangiabao.sixjars.catalog.expenditure

import com.trangiabao.sixjars.database.ExpenditureTypeDB
import com.trangiabao.sixjars.model.ExpenditureType

class ExpenditureTypePresenter(private var view: ExpenditureTypeView) : ExpenditureTypePresenterImpl {

    override fun getAll() {
        view.onGetListResult(ExpenditureTypeDB.getAll())
    }

    override fun add(type: ExpenditureType) {
        view.onAddResult(ExpenditureTypeDB.add(type))
    }

    override fun update(type: ExpenditureType) {
        view.onUpdateResult(ExpenditureTypeDB.update(type))
    }

    override fun delete(id: String) {
        view.onDeleteResult(ExpenditureTypeDB.delete(id))
    }
}