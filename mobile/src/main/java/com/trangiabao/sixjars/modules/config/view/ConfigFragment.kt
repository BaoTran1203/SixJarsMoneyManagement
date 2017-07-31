package com.trangiabao.sixjars.modules.config.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.modules.config.adapter.ConfigApdater
import com.trangiabao.sixjars.modules.config.presenter.ConfigPresenter
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.component.toast.ToastHelper
import kotlinx.android.synthetic.main.fragment_config.view.*

class ConfigFragment : BaseFragment(), ConfigView {

    private var mView: View? = null
    private var adapter: ConfigApdater? = null
    private var presenter: ConfigPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_config, container, false)
        presenter = ConfigPresenter(context, this)
        presenter!!.createView()
        presenter!!.getAll()
        return mView
    }

    override fun onInitControls() {
        mView!!.run {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = ConfigApdater()
            recyclerView.adapter = adapter
        }
    }

    override fun onInitEvents() {
        mView!!.btnSave.setOnClickListener {
            presenter!!.update(adapter!!.List)
        }
    }

    override fun onListLoaded(result: Boolean, msg: String, list: List<Jar>) {
        if (!result) {
            ToastHelper(context).toastError(msg)
        } else {
            adapter!!.List = list.toMutableList()
        }
    }

    override fun onUpdateResult(result: Boolean, msg: String, list: List<Jar>) {
        if (result) {
            adapter!!.List = list.toMutableList()
            ToastHelper(context).toastSuccess(msg)
        } else {
            ToastHelper(context).toastWarning(msg)
        }
    }
}
