package com.trangiabao.sixjars.data.model

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class ExpenditureType : RealmObject() {

    @PrimaryKey
    var id: String? = null
    var type: String? = null
    var description: String? = null
    @LinkingObjects("expenditureType")
    val expenditures: RealmResults<Expenditure>? = null
}
