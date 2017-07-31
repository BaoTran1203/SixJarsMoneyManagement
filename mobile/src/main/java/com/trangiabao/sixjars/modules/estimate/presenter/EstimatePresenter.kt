package com.trangiabao.sixjars.modules.estimate.presenter

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.database.JarDB
import com.trangiabao.sixjars.modules.estimate.view.EstimateView

class EstimatePresenter(var view: EstimateView) : EstimatePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun getAll() {
        val list = JarDB.getAll()
        if (list.isEmpty())
            view.onGetListFailed(R.string.app_name)
        else
            view.onGetListSuccessed(list)
    }
}