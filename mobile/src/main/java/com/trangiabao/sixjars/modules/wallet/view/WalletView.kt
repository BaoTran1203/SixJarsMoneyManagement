package com.trangiabao.sixjars.modules.wallet.view

import com.trangiabao.sixjars.data.model.Wallet
import com.trangiabao.sixjars.utils.base.BaseView

interface WalletView : BaseView {
    fun onGetWalletSuccessed(list: List<Wallet>)
    fun onGetWalletFailed(msg: Int)
}
