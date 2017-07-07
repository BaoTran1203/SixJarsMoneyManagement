package com.trangiabao.sixjars.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.annotations.RealmClass

@RealmClass
open class Database {

    @SerializedName("jars") @Expose
    open var jars: List<Jar>? = null

    @SerializedName("revenue_types") @Expose
    open var revenueTypes: List<RevenueType>? = null

    @SerializedName("expenditure_types") @Expose
    open var expenditureTypes: List<ExpenditureType>? = null

    @SerializedName("revenues") @Expose
    open var revenues: List<Revenue>? = null

    @SerializedName("expenditure1s") @Expose
    open var expenditures: List<Expenditure>? = null
}