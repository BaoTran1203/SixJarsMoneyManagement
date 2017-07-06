package com.trangiabao.sixjars.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.adapter.JarRatioApdater
import com.trangiabao.sixjars.model.Jar
import com.trangiabao.sixjars.presenter.JarPresenter
import com.trangiabao.sixjars.view.JarView
import kotlinx.android.synthetic.main.fragment_jar_ratio.view.*

class JarRatioFragment : Fragment(), JarView {

    private var adapter: JarRatioApdater? = null
    private var presenter: JarPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_jar_ratio, container, false)
        addControls(view)
        addEvents(view)
        return view
    }

    private fun addControls(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = JarRatioApdater()
        view.recyclerView.adapter = adapter
    }

    private fun addEvents(view: View) {
        presenter = JarPresenter(context, this)
        presenter!!.getAll()

        view.btnSave.setOnClickListener {
            presenter!!.update(adapter!!.List)
        }
    }

    override fun onGetListResult(list: MutableList<Jar>) {
        adapter!!.List = list
    }

    override fun onUpdateResult(result: Boolean) {
        if (result) {
            Toast.makeText(context, "Update thanh cong", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Update that bai", Toast.LENGTH_SHORT).show()
        }
    }
}
