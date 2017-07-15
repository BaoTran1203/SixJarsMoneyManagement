package com.trangiabao.sixjars.estimate

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.model.Jar
import com.trangiabao.sixjars.ui.NumericEditText
import kotlinx.android.synthetic.main.fragment_estimate.view.*

class EstimateFragment : BaseFragment(), EstimateView {

    private var adapter: EstimateAdapter? = null
    private var presenter: EstimatePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_estimate, container, false)
        initControls(view)
        initEvents(view)
        initDatabase()
        return view
    }

    private fun initControls(view: View) {
        view.run {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = EstimateAdapter(0.0)
            recyclerView.adapter = adapter
            spinner.setItems((1..20).toMutableList())
        }
    }

    private fun initEvents(view: View) {
        view.run {
            txtSalary.addNumericValueChangedListener(object : NumericEditText.NumericValueWatcher {
                override fun onChanged(newValue: Double) {
                    adapter!!.updateSalary(newValue)
                    txtTotal.text = adapter!!.getTotal()
                }

                override fun onCleared() {
                    txtSalary.setText("0")
                }
            })

            txtSalary.setOnTouchListener(OnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    if (event.rawX >= txtSalary.right - txtSalary.compoundDrawables[2].bounds.width()) {
                        txtSalary.setText("0")
                        return@OnTouchListener true
                    }
                }
                false
            })

            spinner.setOnItemSelectedListener { _, position, _, _ ->
                adapter!!.updateYear(position + 1)
                txtTotal.text = adapter!!.getTotal()
            }
        }
    }

    private fun initDatabase() {
        presenter = EstimatePresenter(context, this)
        presenter!!.getAll()
    }

    override fun onListLoaded(result: Boolean, msg: String, list: List<Jar>) {
        adapter!!.List = list.toMutableList()
        toast(msg)
    }
}
