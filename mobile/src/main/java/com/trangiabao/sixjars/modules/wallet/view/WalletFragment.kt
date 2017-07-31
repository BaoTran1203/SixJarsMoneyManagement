package com.trangiabao.sixjars.modules.wallet.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Wallet
import com.trangiabao.sixjars.modules.wallet.adapter.WalletAdapter
import com.trangiabao.sixjars.modules.wallet.presenter.WalletPresenter
import com.trangiabao.sixjars.utils.helper.NumberHelper
import com.trangiabao.sixjars.utils.helper.ToastHelper
import kotlinx.android.synthetic.main.fragment_wallet.view.*

class WalletFragment : Fragment(), WalletView {

    private var mView: View? = null
    private var presenter: WalletPresenter? = null
    private var adapter: WalletAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_wallet, container, false)
        presenter = WalletPresenter(this)
        presenter!!.createView()
        presenter!!.getListWallets()
        return mView
    }

    override fun onInitControls() {
        mView!!.run {
            adapter = WalletAdapter()
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }

    override fun onInitEvents() {
    }

    override fun onGetListSuccessed(list: List<Wallet>) {
        adapter!!.List = list
        val balance: Double = list.sumByDouble { it.currentAmout!! }
        mView!!.txtBalance.text = NumberHelper.printBigDouble(balance)
    }

    override fun onError(msg: Int) {
        ToastHelper.toastError(context, msg)
    }

}
