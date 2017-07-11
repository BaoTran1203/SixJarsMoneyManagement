package com.trangiabao.sixjars.base.database

import android.util.Log
import com.trangiabao.sixjars.base.model.Revenue
import io.realm.Realm
import io.realm.Sort
import io.realm.exceptions.RealmException
import java.util.*

object RevenueDB {

    private val ID: String = "id"
    private val DATE: String = "date"
    private val TYPE_ID: String = "type.id"

    fun find(from: Date, to: Date): List<Revenue> {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            val query = realm.where(Revenue::class.java).between(DATE, from, to)
            val result = query.findAllSorted(DATE, Sort.ASCENDING)
            val list = realm.copyFromRealm(result).toMutableList()
            realm.close()
            return list
        } catch (e: RealmException) {
            Log.d("TAGTAG", e.printStackTrace().toString())
        }
        return mutableListOf()
    }

    fun update(obj: Revenue): Revenue? {
        try {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val realmModel = realm.copyToRealmOrUpdate(obj)
            val revenue = realm.copyFromRealm(realmModel)
            realm.commitTransaction()
            realm.close()
            return revenue
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return null
    }

    fun delete(id: String): Boolean {
        try {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.where(Revenue::class.java).equalTo(ID, id).findFirst().deleteFromRealm()
            realm.commitTransaction()
            realm.close()
            return true
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return false
    }

    fun find(id: String): Revenue? {
        try {
            val realm = Realm.getDefaultInstance()
            val query = realm.where(Revenue::class.java)
            val result = query.equalTo(ID, id).findFirst()
            val revenue = realm.copyFromRealm(result)
            realm.close()
            return revenue
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return null
    }
}