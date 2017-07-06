package com.trangiabao.sixjars.presenter

import android.content.Context
import com.trangiabao.sixjars.model.ExpenditureType
import com.trangiabao.sixjars.view.ExpenditureTypeView
import io.realm.Realm
import io.realm.exceptions.RealmException

class ExpenditureTypePresenter(context: Context, private var view: ExpenditureTypeView) : ExpenditureTypePresenterImpl {

    private var realm: Realm? = null

    init {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    override fun getAll() {
        var list: MutableList<ExpenditureType> = mutableListOf()
        try {
            list = realm!!.copyFromRealm(realm!!.where(ExpenditureType::class.java).findAll()).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        view.onGetListResult(list)
    }

    override fun get(id: String) {
        try {
            val temp = realm!!.where(ExpenditureType::class.java).equalTo("id", id).findFirst()
            view.onGetObjectResult(realm!!.copyFromRealm(temp))
        } catch (e: RealmException) {
            e.printStackTrace()
            view.onGetObjectResult(null)
        }
    }

    override fun add(type: ExpenditureType) {
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

    override fun update(type: ExpenditureType) {
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
                where(ExpenditureType::class.java).equalTo("id", id).findFirst().deleteFromRealm()
                commitTransaction()
                view.onDeleteResult(true)
            }
        } catch (e: RealmException) {
            e.printStackTrace()
            view.onDeleteResult(false)
        }
    }
}