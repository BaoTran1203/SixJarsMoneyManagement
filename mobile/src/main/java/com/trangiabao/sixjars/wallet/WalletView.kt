package com.trangiabao.sixjars.wallet

import com.trangiabao.sixjars.base.model.Expenditure
import com.trangiabao.sixjars.base.model.Jar
import com.trangiabao.sixjars.base.model.Revenue

interface WalletView {
    fun onListJarLoaded(list: List<Jar>)
    fun onListRevenueLoaded(list: List<Revenue>)
    fun onListExpenditureLoaded(list: List<Expenditure>)
}
