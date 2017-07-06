package com.trangiabao.sixjars.presenter

import android.content.Context
import com.trangiabao.sixjars.model.RevenueType
import com.trangiabao.sixjars.view.RevenueTypeView
import io.realm.Realm
import io.realm.exceptions.RealmException

class RevenueTypePresenter(context: Context, private var view: RevenueTypeView) : RevenueTypePresenterImpl {

    private var realm: Realm? = null

    init {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    override fun getAll() {
        var list: MutableList<RevenueType> = mutableListOf()
        try {
            list = realm!!.copyFromRealm(realm!!.where(RevenueType::class.java).findAll()).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        view.onGetListResult(list)
    }

    override fun get(id: String) {
        try {
            val temp = realm!!.where(RevenueType::class.java).equalTo("id", id).findFirst()
            view.onGetObjectResult(realm!!.copyFromRealm(temp))
        } catch (e: RealmException) {
            e.printStackTrace()
            view.onGetObjectResult(null)
        }
    }

    override fun add(type: RevenueType) {
        try {
            realm!!.run {
                beginTransaction()
                val temp = copyFromRealm(copyToRealm(type))
                commitTransaction()
                view.onAddResult(temp)
            }
        } catch (e: RealmException) {
            e.printStackTrace()
            view.onAddResult(null)
        }
    }

    override fun update(type: RevenueType) {
        try {
            realm!!.run {
                beginTransaction()
                val temp = copyFromRealm(copyToRealmOrUpdate(type))
                commitTransaction()
                view.onUpdateResult(temp)
            }
        } catch (e: RealmException) {
            e.printStackTrace()
            view.onUpdateResult(null)
        }
    }

    override fun delete(id: String) {
        try {
            realm!!.run {
                beginTransaction()
                where(RevenueType::class.java).equalTo("id", id).findFirst().deleteFromRealm()
                commitTransaction()
                view.onDeleteResult(true)
            }
        } catch (e: RealmException) {
            e.printStackTrace()
            view.onDeleteResult(false)
        }
    }
}