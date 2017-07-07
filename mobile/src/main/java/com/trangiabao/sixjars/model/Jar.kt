package com.trangiabao.sixjars.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Jar : RealmObject() {

    @PrimaryKey @SerializedName("name") @Expose
    open var name: String = ""

    @SerializedName("percent") @Expose
    open var percent: Int = 0

    @SerializedName("name_eng") @Expose
    open var nameEng: String = ""

    @SerializedName("description_eng") @Expose
    open var descriptionEng: String = ""

    @SerializedName("name_vie") @Expose
    open var nameVie: String = ""

    @SerializedName("description_vie") @Expose
    open var descriptionVie: String = ""

}