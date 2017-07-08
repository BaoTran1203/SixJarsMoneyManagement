package com.trangiabao.sixjars.database

import android.content.Context
import io.realm.Realm

open class ExpenditureDB(var context: Context) {

    private var realm: Realm
    private val ID: String = "id"
    private val DATE_TIME: String = "datetime"
    private val TYPE: String = "type"

    init {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }
}