package com.trangiabao.sixjars.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class RevenueType : RealmObject() {

    @PrimaryKey @SerializedName("id") @Expose
    open var id: String = ""

    @Required @SerializedName("type") @Expose
    open var type: String = ""

    @SerializedName("description") @Expose
    open var description: String = ""
}