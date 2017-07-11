package com.trangiabao.sixjars.config

import android.content.Context
import com.trangiabao.sixjars.base.database.JarDB
import com.trangiabao.sixjars.base.model.Jar

class ConfigPresenter(var context: Context, var view: ConfigView) : ConfigPresenterImpl {

    override fun getAll() {
        val list = JarDB.getAll()
        view.onListLoaded(list.isNotEmpty(), "${list.size} item(s)", list)
    }

    override fun update(list: List<Jar>) {
        val sum = list.sumBy { x -> x.percent!! }
        if (sum != 100) {
            view.onUpdateResult(false, "Khong du 100%", list)
        } else {
            val newList = JarDB.update(list)
            view.onUpdateResult(newList.isNotEmpty(), "${list.size} item(s)", newList)
        }
    }
}