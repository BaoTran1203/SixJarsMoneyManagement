package com.trangiabao.sixjars.modules.wallet.presenter

import com.trangiabao.sixjars.data.database.ExpenditureDB
import com.trangiabao.sixjars.data.database.JarDB
import com.trangiabao.sixjars.data.database.RevenueDB
import com.trangiabao.sixjars.modules.wallet.view.WalletView

class WalletPresenter(private var view: WalletView) : WalletPresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

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