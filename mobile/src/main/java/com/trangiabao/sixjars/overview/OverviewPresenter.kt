package com.trangiabao.sixjars.overview

import android.content.Context
import com.trangiabao.sixjars.base.database.JarDB

class OverviewPresenter(var context: Context, var view: OverviewView) : OverviewPresenterImpl {

    override fun getAll() {
        view.onListLoaded(JarDB.getAll())
    }

}