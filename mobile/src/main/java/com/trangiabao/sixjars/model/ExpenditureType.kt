package com.trangiabao.sixjars.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ExpenditureType : RealmObject() {

    @PrimaryKey @SerializedName("id") @Expose
    open var id: String? = null

    @SerializedName("type") @Expose
    open var type: String? = null

    @SerializedName("description") @Expose
    open var description: String? = null
}