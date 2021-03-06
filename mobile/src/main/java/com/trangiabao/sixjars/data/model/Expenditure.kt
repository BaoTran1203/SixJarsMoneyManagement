package com.trangiabao.sixjars.data.model

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class Expenditure : RealmObject() {

    @PrimaryKey
    var id: String? = null
    var date: Date? = null
    var amount: Double? = null
    var jar: Jar? = null
    var detail: String? = null

    @LinkingObjects("expenditures")
    val expenditureType: RealmResults<ExpenditureType>? = null
}