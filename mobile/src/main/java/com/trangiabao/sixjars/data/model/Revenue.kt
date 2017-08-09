package com.trangiabao.sixjars.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class Revenue : RealmObject() {

    @PrimaryKey
    var id: String? = null
    var date: Date? = null
    var amount: Double? = null
    var detail: String? = null
    var revenueJars: RealmList<RevenueJar>? = null

    @LinkingObjects("revenues")
    val revenueType: RealmResults<RevenueType>? = null

}