package com.trangiabao.sixjars.modules.config.presenter

import android.content.Context
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.database.JarDB
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.modules.config.view.ConfigView

class ConfigPresenter(var context: Context, var view: ConfigView) : ConfigPresenterImpl {

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

    override fun update(list: List<Jar>) {
        val sum = list.sumBy { x -> x.percent!! }
        if (sum != 100) {
            view.onWarning(R.string.app_name)
        } else {
            val newList = JarDB.update(list)
            if (newList.isEmpty())
                view.onError(R.string.app_name)
            else
                view.onUpdateSuccessed(R.string.app_name, newList)
        }
    }
}