package com.trangiabao.sixjars.overview

import android.content.Context
import com.trangiabao.sixjars.base.database.JarDB

class OverviewPresenter(var context: Context, var view: OverviewView) : OverviewPresenterImpl {

    override fun getAll() {
        val list = JarDB.getAll()
        view.onListLoaded(list.isNotEmpty(), "There is(are) ${list.size} item(s)", list)
    }

}