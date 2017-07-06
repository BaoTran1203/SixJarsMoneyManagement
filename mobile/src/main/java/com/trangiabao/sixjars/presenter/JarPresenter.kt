package com.trangiabao.sixjars.presenter

import android.content.Context
import com.trangiabao.sixjars.model.Jar
import com.trangiabao.sixjars.view.JarView
import io.realm.Realm
import io.realm.exceptions.RealmException

class JarPresenter(context: Context, private var view: JarView) : JarPresenterImpl {

    private var realm: Realm? = null

    init {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    override fun getAll() {
        var list: MutableList<Jar> = mutableListOf()
        try {
            list = realm!!.copyFromRealm(realm!!.where(Jar::class.java).findAll()).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        view.onGetListResult(list)
    }

    override fun update(list: MutableList<Jar>) {
        val sum = list.sumBy { x -> x.percent }
        if (sum != 100) {
            view.onUpdateResult(false)
        } else {
            try {
                realm!!.run {
                    beginTransaction()
                    copyToRealmOrUpdate(list)
                    commitTransaction()
                }
                view.onUpdateResult(true)
            } catch (e: RealmException) {
                e.printStackTrace()
                view.onUpdateResult(false)
            }
        }
    }
}