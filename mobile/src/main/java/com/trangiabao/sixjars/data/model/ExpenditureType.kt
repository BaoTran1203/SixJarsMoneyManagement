package com.trangiabao.sixjars.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ExpenditureType : RealmObject() {

    @PrimaryKey
    var id: String? = null
    var type: String? = null
    var description: String? = null
    var expenditures: RealmList<Expenditure>? = null

}
