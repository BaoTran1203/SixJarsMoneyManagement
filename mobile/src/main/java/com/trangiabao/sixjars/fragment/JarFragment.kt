package com.trangiabao.sixjars.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.adapter.JarApdater
import com.trangiabao.sixjars.model.Jar
import com.trangiabao.sixjars.presenter.JarPresenter
import com.trangiabao.sixjars.view.JarView
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_overview.view.*

class JarFragment : Fragment(), JarView {

    private var adapter: JarApdater? = null
    private var presenter: JarPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_overview, container, false)
        addControl(view)
        presenter = JarPresenter(context, this)
        presenter!!.getAllJar()
        return view
    }

    fun addControl(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = JarApdater()
        view.recyclerView.adapter = adapter
    }

    override fun onJarLoaded(jars: RealmResults<Jar>) {
        adapter!!.updateList(jars)
    }

    override fun onJarUpdateSucessed(result: Boolean) {

    }
}
