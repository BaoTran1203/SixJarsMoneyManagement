package com.trangiabao.sixjars.data.model

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class RevenueJar : RealmObject() {

    @PrimaryKey
    var id: String? = null
    var amount: Double? = null
    var jar: Jar? = null

    @LinkingObjects("revenueJars")
    val revenue: RealmResults<Revenue>? = null

}