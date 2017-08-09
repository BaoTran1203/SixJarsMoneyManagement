package com.trangiabao.sixjars.data.model

import com.trangiabao.sixjars.utils.application.AppConstants
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Jar : RealmObject() {

    @PrimaryKey
    var name: String? = null
    var percent: Int? = null
    private var nameEng: String? = null
    private var descriptionEng: String? = null
    private var nameVie: String? = null
    private var descriptionVie: String? = null
    var balance: Double? = null

    fun getName(lang: String): String {
        if (lang == AppConstants.LANG_CODE_VI)
            return nameVie!!
        return nameEng!!
    }

    fun getDescription(lang: String): String {
        if (lang == AppConstants.LANG_CODE_VI)
            return descriptionVie!!
        return descriptionEng!!
    }
}