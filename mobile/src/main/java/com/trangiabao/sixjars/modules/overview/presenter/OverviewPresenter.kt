package com.trangiabao.sixjars.modules.overview.presenter

import android.content.Context
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.database.JarDB
import com.trangiabao.sixjars.modules.overview.view.OverviewView

class OverviewPresenter(var context: Context, var view: OverviewView) : OverviewPresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun getAll() {
        val list = JarDB.getAll()
        if (list.isEmpty())
            view.onError(R.string.app_name)
        else
            view.onGetListSuccessed(list)
    }

}