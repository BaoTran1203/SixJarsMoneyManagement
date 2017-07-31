package com.trangiabao.sixjars.modules.wallet.presenter

import com.trangiabao.sixjars.utils.base.BasePresenter

interface WalletPresenterImpl : BasePresenter {
    fun getListJar()
    fun getListRevenue()
    fun getListExpenditure()
}