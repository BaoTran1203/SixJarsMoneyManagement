package com.trangiabao.sixjars.modules.wallet.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.modules.wallet.presenter.WalletPresenter

class WalletFragment : Fragment(), WalletView {

    private var mView: View? = null
    private var presenter: WalletPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_wallet, container, false)
        presenter = WalletPresenter(this)
        presenter!!.createView()

        return mView
    }

    override fun onInitControls() {
    }

    override fun onInitEvents() {
    }

    override fun onListJarLoaded(list: List<Jar>) {
    }

    override fun onListRevenueLoaded(list: List<Revenue>) {
    }

    override fun onListExpenditureLoaded(list: List<Expenditure>) {
    }
}
