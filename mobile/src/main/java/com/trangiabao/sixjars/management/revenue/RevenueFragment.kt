package com.trangiabao.sixjars.management.revenue

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.model.Revenue
import kotlinx.android.synthetic.main.fragment_revenue.view.*

class RevenueFragment : BaseFragment(), RevenueView {

    private var _adapter: RevenueAdapter? = null
    private var presenter: RevenuePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_revenue, container, false)
        initControls(view)
        initEvents(view)
        return view
    }

    private fun initEvents(view: View) {
        presenter = RevenuePresenter(context, this)
        //presenter!!.filter()
    }

    private fun initControls(view: View) {
        view.recyclerView!!.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            _adapter = RevenueAdapter(object : RevenueAdapter.ItemClickListener {
                override fun onClickListener(type: Revenue, position: Int) {
                }

                override fun onLongClickListener(type: Revenue, position: Int): Boolean {
                    return true
                }

            })
            adapter = _adapter
        }
    }

    override fun onGetListResult(list: MutableList<Revenue>) {

    }

    override fun onAddResult(obj: Revenue?) {
        if (obj != null) {
            _adapter!!.addItem(obj)
        }
    }

    override fun onUpdateResult(obj: Revenue?) {

    }

    override fun onDeleteResult(result: Boolean) {

    }
}
