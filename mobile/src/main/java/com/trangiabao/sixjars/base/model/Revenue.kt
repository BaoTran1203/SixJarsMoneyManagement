package com.trangiabao.sixjars.base.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Revenue : RealmObject() {

    @PrimaryKey
    var id: String? = null
    var date: Date? = null
    var amount: Double? = null
    var revenueType: RevenueType? = null
    var detail: String? = null

}