package com.trangiabao.sixjars.modules.estimate.presenter

import com.trangiabao.sixjars.data.database.JarDB
import com.trangiabao.sixjars.modules.estimate.view.EstimateView

class EstimatePresenter(var view: EstimateView) : EstimatePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun getAll() {
        val list = JarDB.getAll()
        val result = list.isNotEmpty()
        var msg: String = ""
        if (!result)
            msg = "Load that bai"
        view.onListLoaded(result, msg, list)
    }
}