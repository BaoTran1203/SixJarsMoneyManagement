package com.trangiabao.sixjars.database

import android.content.Context
import android.util.Log
import com.trangiabao.sixjars.model.Revenue
import com.trangiabao.sixjars.model.RevenueType
import io.realm.Case
import io.realm.Realm
import io.realm.exceptions.RealmException
import java.util.*

open class RevenueDB(var context: Context) {

    private var realm: Realm
    private val ID: String = "id"
    private val DATE_TIME: String = "datetime"
    private val TYPE: String = "type"

    init {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    open fun find(id: String): Revenue? {
        var revenue: Revenue? = null
        try {
            val query = realm.where(Revenue::class.java)
            val result = query.equalTo(ID, id).findFirst()
            revenue = realm.copyFromRealm(result)
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return revenue
    }

    open fun find(): MutableList<Revenue> {
        var list: MutableList<Revenue> = mutableListOf()
        try {
            val query = realm.where(Revenue::class.java)
            val result = query.findAll()
            list = realm.copyFromRealm(result).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return list
    }

    open fun find(revenueType: RevenueType): MutableList<Revenue> {
        var list: MutableList<Revenue> = mutableListOf()
        try {
            val query = realm.where(Revenue::class.java)
            val result = query.equalTo("type", revenueType.id, Case.INSENSITIVE).findAll()
            list = realm.copyFromRealm(result).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return list
    }

    open fun find(dateEnd: Date): MutableList<Revenue> {
        var list: MutableList<Revenue> = mutableListOf()
        try {
            val query = realm.where(Revenue::class.java)
            val result = query.lessThanOrEqualTo("datetime", dateEnd).findAll()
            list = realm.copyFromRealm(result).toMutableList()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return list
    }

    open fun find(from: Date, to: Date): MutableList<Revenue> {
        var list: MutableList<Revenue> = mutableListOf()
        try {
            val query = realm.where(Revenue::class.java)
            val result = query.between("datetime", from, to).findAll()
            list = realm.copyFromRealm(result).toMutableList()
        } catch (e: RealmException) {
            Log.d("TAGTAG", e.printStackTrace().toString())
        }
        return list
    }
}