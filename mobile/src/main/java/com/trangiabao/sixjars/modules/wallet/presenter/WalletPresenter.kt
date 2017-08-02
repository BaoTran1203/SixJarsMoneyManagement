package com.trangiabao.sixjars.modules.wallet.presenter

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.database.WalletDB
import com.trangiabao.sixjars.modules.wallet.view.WalletView

class WalletPresenter(private var view: WalletView) : WalletPresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun getListWallets() {
        val list = WalletDB.getAll()
        if (list.isEmpty())
            view.onGetWalletFailed(R.string.get_data_failed)
        else
            view.onGetWalletSuccessed(list)
    }
}