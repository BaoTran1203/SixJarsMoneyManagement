package com.trangiabao.sixjars.modules.m_expenditure_update.presenter

import com.trangiabao.sixjars.R
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
        if (list.isEmpty())
            view.onError(R.string.app_name)
        else
            view.onGetListJarSuccessed(list)
    }

    override fun getAllExpenditureType() {
        val list = ExpenditureTypeDB.getAll()
        if (list.isEmpty())
            view.onError(R.string.app_name)
        else
            view.onGetListExpenditureTypeSuccessed(list)
    }

    override fun updateExpenditure(expenditure: Expenditure) {
        if (expenditure.amount!! < 1.0)
            view.onWarning(R.string.app_name)
        else if (expenditure.expenditureType == null)
            view.onWarning(R.string.app_name)
        else if (expenditure.jar == null)
            view.onWarning(R.string.app_name)
        else {
            val newExpenditure = ExpenditureDB.update(expenditure)
            if (newExpenditure == null)
                view.onError(R.string.app_name)
            else
                view.onUpdateExpenditureSuccessed(R.string.app_name, newExpenditure)
        }
    }

    override fun getExpenditure(id: String) {
        val expenditure: Expenditure? = ExpenditureDB.find(id)
        if (expenditure == null)
            view.onError(R.string.app_name)
        else
            view.onGetExpenditureSuccessed(expenditure)
    }
}