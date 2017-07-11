package com.trangiabao.sixjars.estimate

import android.content.Context
import com.trangiabao.sixjars.base.database.JarDB

class EstimatePresenter(var context: Context, var view: EstimateView) : EstimatePresenterImpl {

    override fun getAll() {
        val list = JarDB.getAll()
        view.onListLoaded(list.isNotEmpty(), "${list.size} item(s)", list)
    }
}