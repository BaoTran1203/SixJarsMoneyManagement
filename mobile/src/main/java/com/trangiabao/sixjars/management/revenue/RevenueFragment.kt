package com.trangiabao.sixjars.management.revenue

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.model.Revenue
import com.trangiabao.sixjars.management.add_revenue.AddRevenueActivity
import com.trangiabao.sixjars.management.edit_revenue.EditRevenueActivity
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.custom_layout_date_filter.view.*
import kotlinx.android.synthetic.main.fragment_revenue.view.*
import java.text.SimpleDateFormat
import java.util.*


class RevenueFragment : BaseFragment(), RevenueView {

    private var _adapter: RevenueAdapter? = null
    private var presenter: RevenuePresenter? = null
    private var dateFormat: SimpleDateFormat? = null
    private var dateFrom: Date = Date()
    private var dateTo: Date = Date()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_revenue, container, false)
        initControls(view)
        initEvents(view)
        initDatabase()
        return view
    }

    private fun initControls(view: View) {
        view.run {
            recyclerView!!.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                _adapter = RevenueAdapter(object : RevenueAdapter.ItemClickListener {
                    override fun onClickListener(type: Revenue, position: Int) {
                        val intent = Intent(context, EditRevenueActivity::class.java)
                        intent.putExtra("type.id", type.id)
                        startActivity(intent)
                    }

                    override fun onLongClickListener(type: Revenue, position: Int): Boolean {
                        if (confirmDelete()) {
                            presenter!!.delete(type.id!!)
                            _adapter!!.removeItem(position)
                        }
                        return true
                    }
                })
                adapter = _adapter
            }
            dateFormat = SimpleDateFormat(getString(R.string.date_format), Locale.US)
            txtDateFrom.text = dateFormat!!.format(dateFrom)
            txtDateTo.text = dateFormat!!.format(dateTo)
        }
    }

    private fun initEvents(view: View) {
        view.run {
            txtDateFrom.setOnClickListener {
                val cal = Calendar.getInstance()
                cal.time = dateFrom
                val dpd = DatePickerDialog.newInstance({ _, year, month, day ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    cal.set(Calendar.DAY_OF_MONTH, day)
                    dateFrom = cal.time
                    txtDateFrom.text = dateFormat!!.format(dateFrom)
                    presenter!!.filter(dateFrom, dateTo)
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
                dpd.setVersion(DatePickerDialog.Version.VERSION_1)
                dpd.show(activity.fragmentManager, "")
            }

            txtDateTo.setOnClickListener {
                val cal = Calendar.getInstance()
                cal.time = dateTo
                val dpd = DatePickerDialog.newInstance({ _, year, month, day ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    cal.set(Calendar.DAY_OF_MONTH, day)
                    dateTo = cal.time
                    txtDateTo.text = dateFormat!!.format(dateTo)
                    presenter!!.filter(dateFrom, dateTo)
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
                dpd.setVersion(DatePickerDialog.Version.VERSION_1)
                dpd.show(activity.fragmentManager, "")
            }

            fab.setOnClickListener {
                startActivity(Intent(context, AddRevenueActivity::class.java))
            }
        }
    }

    private fun initDatabase() {
        presenter = RevenuePresenter(this)
        presenter!!.filter(dateFrom, dateTo)
    }

    override fun onGetListResult(list: List<Revenue>) {
        _adapter!!.updateList(list.toMutableList())
    }

    override fun onAddResult(obj: Revenue?) {
        _adapter!!.addItem(obj!!)
    }

    override fun onUpdateResult(obj: Revenue?) {
        _adapter!!.updateItem(obj!!)
    }

    override fun onDeleteResult(result: Boolean) {
        if (!result) {
            toast("Failed")
        }
    }
}
