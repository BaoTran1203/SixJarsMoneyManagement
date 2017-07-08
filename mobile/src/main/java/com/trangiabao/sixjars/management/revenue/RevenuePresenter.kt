package com.trangiabao.sixjars.management.revenue

import android.content.Context
import com.trangiabao.sixjars.model.Revenue
import com.trangiabao.sixjars.model.RevenueType
import io.realm.Case
import io.realm.Realm
import io.realm.RealmResults
import io.realm.exceptions.RealmException
import java.util.*

class RevenuePresenter(context: Context, private var view: RevenueView) : RevenuePresenterImpl {

    private var realm: Realm? = null

    init {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    override fun filter() {
        var list: MutableList<Revenue> = mutableListOf()
        try {
            list = realm!!.copyFromRealm(realm!!.where(Revenue::class.java).findAll()).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        view.onGetListResult(list)
    }

    override fun filter(type: RevenueType) {
        var list: MutableList<Revenue> = mutableListOf()
        try {
            val query = realm!!.where(Revenue::class.java)
            val results = query.contains("type", type.id, Case.INSENSITIVE).findAll()
            list = realm!!.copyFromRealm(results).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        view.onGetListResult(list)
    }

    override fun filter(dateStart: Date?, dateEnd: Date) {
        var list: MutableList<Revenue> = mutableListOf()
        try {
            val query = realm!!.where(Revenue::class.java)
            val results: RealmResults<Revenue>
            if (dateStart == null) {
                results = query.lessThanOrEqualTo("datetime", dateEnd).findAll()
            } else {
                results = query.between("datetime", dateStart, dateEnd).findAll()
            }
            list = realm!!.copyFromRealm(results).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        view.onGetListResult(list)
    }

    override fun filter(dateStart: Date?, dateEnd: Date, type: RevenueType) {
        var list: MutableList<Revenue> = mutableListOf()
        try {
            val query = realm!!.where(Revenue::class.java)
            val results: RealmResults<Revenue>
            if (dateStart == null) {
                results = query.lessThanOrEqualTo("datetime", dateEnd)
                        .contains("type", type.id, Case.INSENSITIVE).findAll()
            } else {
                results = query.between("datetime", dateStart, dateEnd)
                        .contains("type", type.id, Case.INSENSITIVE).findAll()
            }
            list = realm!!.copyFromRealm(results).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        view.onGetListResult(list)
    }

    override fun add(revenue: Revenue) {
        try {
            realm!!.run {
                beginTransaction()
                val temp = copyFromRealm(copyToRealm(revenue))
                commitTransaction()
                view.onAddResult(temp)
            }
        } catch (e: RealmException) {
            e.printStackTrace()
            view.onAddResult(null)
        }
    }

    override fun update(revenue: Revenue) {
        try {
            realm!!.run {
                beginTransaction()
                val temp = copyFromRealm(copyToRealmOrUpdate(revenue))
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
                where(Revenue::class.java).equalTo("id", id).findFirst().deleteFromRealm()
                commitTransaction()
                view.onDeleteResult(true)
            }
        } catch (e: RealmException) {
            e.printStackTrace()
            view.onDeleteResult(false)
        }
    }
}