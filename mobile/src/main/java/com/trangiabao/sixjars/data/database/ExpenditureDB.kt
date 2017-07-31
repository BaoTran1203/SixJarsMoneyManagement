package com.trangiabao.sixjars.data.database

import com.trangiabao.sixjars.data.model.Expenditure
import io.realm.Realm
import io.realm.Sort
import io.realm.exceptions.RealmException
import java.util.*

object ExpenditureDB {

    private val ID: String = "id"
    private val DATE: String = "date"
    private val TYPE_ID: String = "type.id"
    private val JAR_NAME: String = "jar.name"

    fun getAll(): List<Expenditure> {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            val query = realm.where(Expenditure::class.java)
            val result = query.findAll()
            val list = realm.copyFromRealm(result).toMutableList()
            realm.close()
            return list
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return mutableListOf()
    }

    fun find(from: Date, to: Date): List<Expenditure>? {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            val query = realm.where(Expenditure::class.java).between(ExpenditureDB.DATE, from, to)
            val result = query.findAllSorted(ExpenditureDB.DATE, Sort.ASCENDING)
            val list = realm.copyFromRealm(result).toMutableList()
            realm.close()
            return list
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return null
    }

    fun update(obj: Expenditure): Expenditure? {
        try {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val realmModel = realm.copyToRealmOrUpdate(obj)
            val Expenditure = realm.copyFromRealm(realmModel)
            realm.commitTransaction()
            realm.close()
            return Expenditure
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return null
    }

    fun delete(id: String): Boolean {
        try {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.where(Expenditure::class.java).equalTo(ExpenditureDB.ID, id).findFirst().deleteFromRealm()
            realm.commitTransaction()
            realm.close()
            return true
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return false
    }

    fun find(id: String): Expenditure? {
        try {
            val realm = Realm.getDefaultInstance()
            val query = realm.where(Expenditure::class.java)
            val result = query.equalTo(ExpenditureDB.ID, id).findFirst()
            val Expenditure = realm.copyFromRealm(result)
            realm.close()
            return Expenditure
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return null
    }
}