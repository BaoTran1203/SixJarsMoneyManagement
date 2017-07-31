package com.trangiabao.sixjars.modules.wallet.view

import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.utils.base.BaseView

interface WalletView : BaseView {
    fun onListJarLoaded(list: List<Jar>)
    fun onListRevenueLoaded(list: List<Revenue>)
    fun onListExpenditureLoaded(list: List<Expenditure>)
}
