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
    open var id: String? = null

    @SerializedName("datetime") @Expose
    open var datetime: Date? = null

    @SerializedName("amount") @Expose
    open var amount: Double? = null

    @SerializedName("jar") @Expose
    open var jar: Jar? = null

    @SerializedName("type") @Expose
    open var type: ExpenditureType? = null

    @SerializedName("detail") @Expose
    open var detail: String? = null
}