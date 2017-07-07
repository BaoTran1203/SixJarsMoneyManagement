package com.trangiabao.sixjars.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.annotations.RealmClass

@RealmClass
open class Database {

    @SerializedName("jars") @Expose
    open var jars: List<Jar> = listOf()

    @SerializedName("revenue_types") @Expose
    open var revenueTypes: List<RevenueType> = listOf()

    @SerializedName("expenditure_types") @Expose
    open var expenditureTypes: List<ExpenditureType> = listOf()

    @SerializedName("revenues") @Expose
    open var revenues: List<Revenue> = listOf()

    @SerializedName("expenditure1s") @Expose
    open var expenditures: List<Expenditure> = listOf()
}