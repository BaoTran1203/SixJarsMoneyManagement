package com.trangiabao.sixjars.base.database

import com.trangiabao.sixjars.base.model.RevenueType
import io.realm.Realm
import io.realm.exceptions.RealmException

object RevenueTypeDB {

    private val ID: String = "id"
    private val ID_DEFAULT: String = "9c528365-6eab-494c-83d3-24ef5ee6df10"

    fun getAll(): List<RevenueType> {
        val realm = Realm.getDefaultInstance()
        try {
            val query = realm.where(RevenueType::class.java)
            val result = query.findAll()
            return realm.copyFromRealm(result)
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return mutableListOf()
    }

    fun add(obj: RevenueType): RevenueType? {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            val realmModel = realm.copyToRealm(obj)
            realm.commitTransaction()
            return realm.copyFromRealm(realmModel)
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return null
    }

    fun update(obj: RevenueType): RevenueType? {
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            val realmModel = realm.copyToRealmOrUpdate(obj)
            realm.commitTransaction()
            return realm.copyFromRealm(realmModel)
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return null
    }

    fun delete(id: String): Boolean {
        if (isUsed(id))
            return false
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.where(RevenueType::class.java).equalTo(ID, id).findAll().deleteFirstFromRealm()
            realm.commitTransaction()
            return true
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return false
    }

    fun isUsed(id: String): Boolean {
        val realm = Realm.getDefaultInstance()
        try {
            val query = realm.where(RevenueType::class.java)
            val result = query.equalTo(ID, id).findFirst()
            return result.revenues!!.isNotEmpty()
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return false
    }
}