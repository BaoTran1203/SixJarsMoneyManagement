package com.trangiabao.sixjars.modules.overview.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.modules.overview.adapter.OverviewApdater
import com.trangiabao.sixjars.modules.overview.presenter.OverviewPresenter
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.helper.ToastHelper
import kotlinx.android.synthetic.main.fragment_overview.view.*

class OverviewFragment : BaseFragment(), OverviewView {

    private var mView: View? = null
    private var adapter: OverviewApdater? = null
    private var presenter: OverviewPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_overview, container, false)
        presenter = OverviewPresenter(context, this)
        presenter!!.createView()
        presenter!!.getAll()
        return mView
    }

    override fun onInitControls() {
        mView!!.run {
            adapter = OverviewApdater()
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }

    override fun onInitEvents() {
    }

    override fun onGetListSuccessed(list: List<Jar>) {
        adapter!!.List = list
    }

    override fun onGetListFailed(msg: Int) {
        ToastHelper.toastError(context, msg)
    }
}
