package com.trangiabao.sixjars.base.database

import com.trangiabao.sixjars.base.model.Jar
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
        var list: List<Jar> = listOf()
        try {
            val realm: Realm = Realm.getDefaultInstance()
            val query = realm.where(Jar::class.java)
            val result = query.findAll()
            list = realm.copyFromRealm(result)
            realm.close()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return list
    }

    fun update(list: List<Jar>): Boolean {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(list)
            realm.commitTransaction()
            realm.close()
            return true
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return false
    }
}