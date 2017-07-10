package com.trangiabao.sixjars.base.database

import com.trangiabao.sixjars.base.model.RevenueType
import io.realm.Realm
import io.realm.exceptions.RealmException

object RevenueTypeDB {

    private val ID: String = "id"

    fun getAll(): List<RevenueType> {
        try {
            val realm = Realm.getDefaultInstance()
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
            val realm = Realm.getDefaultInstance()
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
            val realm = Realm.getDefaultInstance()
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
        if (checkExist(id))
            return false
        try {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.where(RevenueType::class.java).equalTo(ID, id).findAll().deleteFirstFromRealm()
            realm.commitTransaction()
            realm.close()
            return true
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return false
    }

    private fun checkExist(id: String): Boolean {
        val realm = Realm.getDefaultInstance()
        try {
            val type = realm.where(RevenueType::class.java).equalTo(ID, id).findFirst()
            val lstRevenue = type.revenues
            return lstRevenue!!.size > 0
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return false
    }
}