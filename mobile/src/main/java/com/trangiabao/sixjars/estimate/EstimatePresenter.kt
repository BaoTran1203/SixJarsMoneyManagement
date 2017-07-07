package com.trangiabao.sixjars.estimate

import android.content.Context
import com.trangiabao.sixjars.database.JarDB
import com.trangiabao.sixjars.overview.OverviewPresenterImpl

class EstimatePresenter(var context: Context, var view: EstimateView) : OverviewPresenterImpl {

    override fun getAll() {
        view.onListLoaded(JarDB(context).find())
    }
}