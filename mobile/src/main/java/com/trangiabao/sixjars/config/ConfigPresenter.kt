package com.trangiabao.sixjars.config

import android.content.Context
import com.trangiabao.sixjars.base.database.JarDB
import com.trangiabao.sixjars.base.model.Jar

class ConfigPresenter(var context: Context, var view: ConfigView) : ConfigPresenterImpl {

    override fun getAll() {
        view.onListLoaded(JarDB.getAll())
    }

    override fun update(list: List<Jar>) {
        val sum = list.sumBy { x -> x.percent!! }
        if (sum != 100) {
            view.onUpdateResult(false, "Khong du 100%")
        } else {
            val result: Boolean = JarDB.update(list)
            view.onUpdateResult(result, if (result) "Successed" else "Failed")
        }
    }
}