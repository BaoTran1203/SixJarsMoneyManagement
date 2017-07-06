package com.trangiabao.sixjars.view

import com.trangiabao.sixjars.model.ExpenditureType
import com.trangiabao.sixjars.model.RevenueType
import io.realm.RealmResults

interface CatalogView {
    fun onExpenditureTypeLoaded(types: MutableList<ExpenditureType>)
    fun onRevenueTypeLoaded(types: MutableList<RevenueType>)
}