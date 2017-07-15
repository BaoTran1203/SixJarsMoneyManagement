package com.trangiabao.sixjars.wallet

import com.trangiabao.sixjars.base.database.ExpenditureDB
import com.trangiabao.sixjars.base.database.JarDB
import com.trangiabao.sixjars.base.database.RevenueDB

class WalletPresenter(private var view: WalletView) : WalletPresenterImpl {

    override fun getListJar() {
        view.onListJarLoaded(JarDB.getAll())
    }

    override fun getListRevenue() {
        view.onListRevenueLoaded(RevenueDB.getAll())
    }

    override fun getListExpenditure() {
        view.onListExpenditureLoaded(ExpenditureDB.getAll())
    }
}