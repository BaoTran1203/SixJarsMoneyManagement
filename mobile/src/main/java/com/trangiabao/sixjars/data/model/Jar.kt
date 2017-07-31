package com.trangiabao.sixjars.data.model

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class Jar : RealmObject() {

    @PrimaryKey
    var name: String? = null
    var percent: Int? = null
    var nameEng: String? = null
    var descriptionEng: String? = null
    var nameVie: String? = null
    var descriptionVie: String? = null
    @LinkingObjects("jar")
    val expenditures: RealmResults<Expenditure>? = null

}