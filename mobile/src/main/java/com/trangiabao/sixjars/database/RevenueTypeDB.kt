package com.trangiabao.sixjars.database

import com.trangiabao.sixjars.model.RevenueType
import io.realm.Realm
import io.realm.exceptions.RealmException

object RevenueTypeDB {

    private val ID: String = "id"

    fun getAll(): List<RevenueType> {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            val query = realm.where(RevenueType::class.java)
            val result = query.findAll()
            val list = realm.copyFromRealm(result)
            realm.close()
            return list
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return mutableListOf()
    }

    fun add(obj: RevenueType): RevenueType? {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val realmModel = realm.copyToRealm(obj)
            val revenueType = realm.copyFromRealm(realmModel)
            realm.commitTransaction()
            realm.close()
            return revenueType
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return null
    }

    fun update(obj: RevenueType): RevenueType? {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val realmModel = realm.copyToRealmOrUpdate(obj)
            val revenueType = realm.copyFromRealm(realmModel)
            realm.commitTransaction()
            realm.close()
            return revenueType
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return null
    }

    fun delete(id: String): Boolean {
        if (id == "9c528365-6eab-494c-83d3-24ef5ee6df10")
            return false
        try {
            val realm: Realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.where(RevenueType::class.java).equalTo(ID, id).findFirst().deleteFromRealm()
            realm.commitTransaction()
            realm.close()
            return true
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return false
    }
}