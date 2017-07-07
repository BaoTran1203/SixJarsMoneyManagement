package com.trangiabao.sixjars.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.adapter.JarEstimateAdapter
import com.trangiabao.sixjars.model.Jar
import com.trangiabao.sixjars.presenter.JarPresenter
import com.trangiabao.sixjars.system.BaseFragment
import com.trangiabao.sixjars.ui.NumericEditText
import com.trangiabao.sixjars.view.JarView
import kotlinx.android.synthetic.main.fragment_estimate.view.*

class EstimateFragment : BaseFragment(), JarView {

    private var adapter: JarEstimateAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_estimate, container, false)
        addControls(view)
        addEvents(view)
        return view
    }

    private fun addControls(view: View) {
        view.run {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = JarEstimateAdapter(0.0)
            recyclerView.adapter = adapter
            spinner.setItems((1..20).toMutableList())
        }
    }

    private fun addEvents(view: View) {
        val presenter: JarPresenter = JarPresenter(context, this)
        presenter.getAll()
        view.run {
            txtSalary.addNumericValueChangedListener(object : NumericEditText.NumericValueWatcher {
                override fun onChanged(newValue: Double) {
                    adapter!!.updateSalary(newValue)
                    txtTotal.text = adapter!!.getTotal()
                }

                override fun onCleared() {
                }
            })

            spinner.setOnItemSelectedListener { _, position, _, _ ->
                adapter!!.updateYear(position + 1)
                txtTotal.text = adapter!!.getTotal()
            }
        }
    }

    override fun onGetListResult(list: MutableList<Jar>) {
        adapter!!.List = list
    }

    override fun onUpdateResult(result: Boolean) {
        // TODO("Do nothing")
    }
}
