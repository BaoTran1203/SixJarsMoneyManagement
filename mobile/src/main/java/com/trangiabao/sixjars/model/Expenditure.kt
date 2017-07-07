package com.trangiabao.sixjars.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class Expenditure : RealmObject() {

    @PrimaryKey @SerializedName("id") @Expose
    open var id: String = ""

    @SerializedName("datetime") @Expose
    open var datetime: Date = Date()

    @SerializedName("amount") @Expose
    open var amount: Double = 0.0

    @SerializedName("jar") @Expose
    open var jar: Jar = Jar()

    @SerializedName("type") @Expose
    open var type: ExpenditureType = ExpenditureType()

    @SerializedName("detail") @Expose
    open var detail: String = ""
}