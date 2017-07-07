package com.trangiabao.sixjars.database

import android.content.Context
import com.trangiabao.sixjars.model.Jar
import io.realm.Realm
import io.realm.exceptions.RealmException

class JarDB(var context: Context) {

    private var realm: Realm

    init {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    fun find(name: String): Jar? {
        var model: Jar? = null
        try {
            val query = realm.where(Jar::class.java)
            val result = query.equalTo(model!!.NAME, name).findFirst()
            model = realm.copyFromRealm(result)
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return model
    }

    fun find(): MutableList<Jar> {
        var list: MutableList<Jar> = mutableListOf()
        try {
            val query = realm.where(Jar::class.java)
            val result = query.findAll()
            list = realm.copyFromRealm(result)
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return list
    }

    fun update(list: MutableList<Jar>): Boolean {
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(list)
            realm.commitTransaction()
            return true
        } catch (e: RealmException) {
            e.printStackTrace()
        }
        return false
    }
}