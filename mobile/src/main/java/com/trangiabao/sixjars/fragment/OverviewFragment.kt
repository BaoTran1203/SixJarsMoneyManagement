package com.trangiabao.sixjars.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.adapter.JarOverviewApdater
import com.trangiabao.sixjars.model.Jar
import com.trangiabao.sixjars.presenter.JarPresenter
import com.trangiabao.sixjars.system.BaseFragment
import com.trangiabao.sixjars.view.JarView
import kotlinx.android.synthetic.main.fragment_overview.view.*

class OverviewFragment : BaseFragment(), JarView {

    private var adapter: JarOverviewApdater? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_overview, container, false)
        addControl(view)
        val presenter: JarPresenter = JarPresenter(context, this)
        presenter.getAll()
        return view
    }

    fun addControl(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = JarOverviewApdater()
        view.recyclerView.adapter = adapter
    }

    override fun onGetListResult(list: MutableList<Jar>) {
        adapter!!.List = list
    }

    override fun onUpdateResult(result: Boolean) {
        // TODO("Do nothing")
    }
}
