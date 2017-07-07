package com.trangiabao.sixjars.overview

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.model.Jar
import com.trangiabao.sixjars.system.BaseFragment
import kotlinx.android.synthetic.main.fragment_overview.view.*

class OverviewFragment : BaseFragment(), OverviewView {

    private var adapter: OverviewApdater? = null
    private var presenter: OverviewPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_overview, container, false)
        initControl(view)
        initDatabase()
        return view
    }

    fun initControl(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = OverviewApdater()
        view.recyclerView.adapter = adapter
    }

    private fun initDatabase() {
        presenter = OverviewPresenter(context, this)
        presenter!!.getAll()
    }

    override fun onListLoaded(list: MutableList<Jar>) {
        adapter!!.List = list
    }
}
