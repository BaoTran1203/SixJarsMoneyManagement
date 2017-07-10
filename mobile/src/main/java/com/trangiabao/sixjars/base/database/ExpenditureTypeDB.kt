package com.trangiabao.sixjars.base.database

import com.trangiabao.sixjars.base.model.ExpenditureType
import io.realm.Realm
import io.realm.exceptions.RealmException

object ExpenditureTypeDB {

    private val ID: String = "id"

    fun getAll(): List<ExpenditureType> {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            val query = realm.where(ExpenditureType::class.java)
            val result = query.findAll()
            val list = realm.copyFromRealm(result)
            realm.close()
            return list
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return mutableListOf()
    }

    fun add(obj: ExpenditureType): ExpenditureType? {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val realmModel = realm.copyToRealm(obj)
            val expenditureType = realm.copyFromRealm(realmModel)
            realm.commitTransaction()
            realm.close()
            return expenditureType
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return null
    }

    fun update(obj: ExpenditureType): ExpenditureType? {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val realmModel = realm.copyToRealmOrUpdate(obj)
            val expenditureType = realm.copyFromRealm(realmModel)
            realm.commitTransaction()
            realm.close()
            return expenditureType
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return null
    }

    fun delete(id: String): Boolean {
        if (id == "a7500f91-1f6e-412b-8b75-30203801b62b")
            return false
        try {
            val realm: Realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.where(ExpenditureType::class.java).equalTo(ID, id).findFirst().deleteFromRealm()
            realm.commitTransaction()
            realm.close()
            return true
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return false
    }
}