package com.trangiabao.sixjars.modules.m_expenditure_update.presenter

import com.trangiabao.sixjars.data.database.ExpenditureDB
import com.trangiabao.sixjars.data.database.ExpenditureTypeDB
import com.trangiabao.sixjars.data.database.JarDB
import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.modules.m_expenditure_update.view.UpdateExpenditureView

class UpdateExpenditurePresenter(private var view: UpdateExpenditureView) : UpdateExpenditurePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

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