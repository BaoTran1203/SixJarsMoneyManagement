package com.trangiabao.sixjars.modules.estimate.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.modules.estimate.adapter.EstimateAdapter
import com.trangiabao.sixjars.modules.estimate.presenter.EstimatePresenter
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.component.NumericEditText
import com.trangiabao.sixjars.utils.component.toast.ToastHelper
import kotlinx.android.synthetic.main.fragment_estimate.*
import kotlinx.android.synthetic.main.fragment_estimate.view.*

class EstimateFragment : BaseFragment(), EstimateView {

    private var mView: View? = null
    private var adapter: EstimateAdapter? = null
    private var presenter: EstimatePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_estimate, container, false)
        presenter = EstimatePresenter(this)
        presenter!!.createView()
        presenter!!.getAll()
        return mView
    }

    override fun onInitControls() {
        mView!!.run {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = EstimateAdapter(0.0)
            recyclerView.adapter = adapter
            val list: MutableList<String> = mutableListOf()
            (1..20).mapTo(list) { it.toString() }
            val dataAdapter = ArrayAdapter<String>(context, R.layout.item_spinner, list)
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = dataAdapter
            spinner.setSelection(0)
        }
    }

    override fun onInitEvents() {
        mView!!.run {
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

            spinner.onItemSelectedListener = MyProcessEvent()
        }
    }

    override fun onListLoaded(result: Boolean, msg: String, list: List<Jar>) {
        if (!result)
            ToastHelper(context).toastError(msg)
        else
            adapter!!.List = list.toMutableList()
    }

    private inner class MyProcessEvent : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, arg3: Long) {
            adapter!!.updateYear(position + 1)
            txtTotal.text = adapter!!.getTotal()
        }

        override fun onNothingSelected(arg0: AdapterView<*>) {
        }
    }
}