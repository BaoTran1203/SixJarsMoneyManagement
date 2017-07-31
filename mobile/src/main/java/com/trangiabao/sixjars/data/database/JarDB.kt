package com.trangiabao.sixjars.data.database

import com.trangiabao.sixjars.data.model.Jar
import io.realm.Realm
import io.realm.exceptions.RealmException

object JarDB {

    private val NAME: String = "name"
    private val PERCENT: String = "percent"

    /*fun findByName(name: String): Jar? {
        var model: Jar? = null
        try {
            val query = realm.where(Jar::class.java)
            val result = query.equalTo(NAME, name).findFirst()
            model = realm.copyFromRealm(result)
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return model
    }*/

    fun getAll(): List<Jar> {
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val query = realm.where(Jar::class.java)
            val result = query.findAll()
            return realm.copyFromRealm(result)
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return listOf()
    }

    fun update(list: List<Jar>): List<Jar> {
        val realm: Realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            val result = realm.copyToRealmOrUpdate(list)
            realm.commitTransaction()
            return realm.copyFromRealm(result)
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return listOf()
    }
}