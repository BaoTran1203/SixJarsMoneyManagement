package com.trangiabao.sixjars.presenter

import android.content.Context
import com.trangiabao.sixjars.model.ExpenditureType
import com.trangiabao.sixjars.model.RevenueType
import com.trangiabao.sixjars.view.CatalogView
import io.realm.Realm

class CatalogPresenter(private var context: Context, private var view: CatalogView) : CatalogPresenterImpl {

    override fun getExpenditureType() {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        val list: MutableList<ExpenditureType> = realm.where(ExpenditureType::class.java).findAll().toMutableList()
        view.onExpenditureTypeLoaded(list)
        realm.close()
    }

    override fun getRevenueType() {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        val list: MutableList<RevenueType> = realm.where(RevenueType::class.java).findAll().toMutableList()
        view.onRevenueTypeLoaded(list)
        realm.close()
    }
}