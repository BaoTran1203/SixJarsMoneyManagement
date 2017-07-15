package com.trangiabao.sixjars.management.expenditure_update

import com.trangiabao.sixjars.base.database.ExpenditureDB
import com.trangiabao.sixjars.base.database.ExpenditureTypeDB
import com.trangiabao.sixjars.base.database.JarDB
import com.trangiabao.sixjars.base.model.Expenditure

class UpdateExpenditurePresenter(private var view: UpdateExpenditureView) : UpdateExpenditurePresenterImpl {

    override fun getAllJar() {
        val list = JarDB.getAll()
        view.onListJarLoaded(list.isNotEmpty(), "", list)
    }

    override fun getAllExpenditureType() {
        val list = ExpenditureTypeDB.getAll()
        view.onListExpenditureTypeLoaded(list.isNotEmpty(), "", list)
    }

    override fun updateExpenditure(expenditure: Expenditure) {
        val newExpenditure = ExpenditureDB.update(expenditure)
        view.onUpdateExpenditureResult(newExpenditure != null, "", newExpenditure)
    }

    override fun getExpenditure(id: String) {
        if (id == "")
            view.onGetExpenditure(true, "Add", null)
        else {
            val obj: Expenditure? = ExpenditureDB.find(id)
            view.onGetExpenditure(obj == null, "Edit", obj)
        }
    }
}