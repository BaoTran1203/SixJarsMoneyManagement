package com.trangiabao.sixjars.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.adapter.RevenueTypeAdapter
import com.trangiabao.sixjars.model.ExpenditureType
import com.trangiabao.sixjars.model.RevenueType
import com.trangiabao.sixjars.presenter.CatalogPresenter
import com.trangiabao.sixjars.view.CatalogView
import kotlinx.android.synthetic.main.fragment_type.view.*

class RevenueTypeFragment : Fragment(), CatalogView {

    private var adapter: RevenueTypeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_type, container, false)
        setupUI(view)
        val presenter = CatalogPresenter(context, this)
        presenter.getRevenueType()
        return view
    }

    private fun setupUI(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RevenueTypeAdapter(object : RevenueTypeAdapter.ItemClickListener {
            override fun onClickListener(type: RevenueType, position: Int) {
                // Edit
                Log.d("TAGTAG", "Click at $position")
            }

            override fun onLongClickListener(type: RevenueType, position: Int): Boolean {
                // Delete
                Log.d("TAGTAG", "Long Click at $position")
                return true
            }

        })
        view.recyclerView.adapter = adapter
    }

    override fun onExpenditureTypeLoaded(types: MutableList<ExpenditureType>) {
        TODO("not implemented")
    }

    override fun onRevenueTypeLoaded(types: MutableList<RevenueType>) {
        adapter!!.updateList(types)
    }
}
