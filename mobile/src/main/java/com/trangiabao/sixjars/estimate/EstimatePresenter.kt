package com.trangiabao.sixjars.estimate

import android.content.Context
import com.trangiabao.sixjars.database.JarDB

class EstimatePresenter(var context: Context, var view: EstimateView) : EstimatePresenterImpl {

    override fun getAll() {
        view.onListLoaded(JarDB.getAll())
    }
}