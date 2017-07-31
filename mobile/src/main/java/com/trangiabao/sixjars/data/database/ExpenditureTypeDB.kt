package com.trangiabao.sixjars.data.database

import com.trangiabao.sixjars.data.model.ExpenditureType
import io.realm.Realm
import io.realm.exceptions.RealmException

object ExpenditureTypeDB {

    private val ID: String = "id"
    private val ID_DEFAULT: String = "a7500f91-1f6e-412b-8b75-30203801b62b"

    fun getAll(): List<ExpenditureType> {
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val query = realm.where(ExpenditureType::class.java)
            val result = query.findAll()
            return realm.copyFromRealm(result)
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return mutableListOf()
    }

    fun update(obj: ExpenditureType): ExpenditureType? {
        val realm: Realm = Realm.getDefaultInstance()
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
        val realm: Realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.where(ExpenditureType::class.java).equalTo(ID, id).findFirst().deleteFromRealm()
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
            val query = realm.where(ExpenditureType::class.java)
            val result = query.equalTo(ID, id).findFirst()
            return result.expenditures!!.isNotEmpty()
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return false
    }
}