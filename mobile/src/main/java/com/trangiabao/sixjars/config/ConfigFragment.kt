package com.trangiabao.sixjars.config

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.model.Jar
import com.trangiabao.sixjars.system.BaseFragment
import kotlinx.android.synthetic.main.fragment_config.view.*

class ConfigFragment : BaseFragment(), ConfigView {

    private var adapter: ConfigApdater? = null
    private var presenter: ConfigPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_config, container, false)
        initControls(view)
        initEvents(view)
        initDatabase()
        return view
    }

    private fun initControls(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ConfigApdater()
        view.recyclerView.adapter = adapter
    }

    private fun initEvents(view: View) {
        view.btnSave.setOnClickListener {
            presenter!!.update(adapter!!.List)
        }
    }

    private fun initDatabase() {
        presenter = ConfigPresenter(context, this)
        presenter!!.getAll()
    }

    override fun onListLoaded(list: MutableList<Jar>) {
        adapter!!.List = list
    }

    override fun onUpdateResult(result: Boolean, msg: String) {
        toast(msg)
    }
}
