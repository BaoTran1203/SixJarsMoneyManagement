package com.trangiabao.sixjars.management.expenditure

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.base.model.Expenditure
import com.trangiabao.sixjars.management.expenditure_update.UpdateExpenditureActivity
import com.trangiabao.sixjars.ui.ScrollAwareFABBehavior
import com.trangiabao.sixjars.ui.dialog.CustomDialogConfirm
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_expenditure.view.*
import kotlinx.android.synthetic.main.layout_date_filter.view.*
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormatter


class ExpenditureFragment : BaseFragment(), ExpenditureView {

    private var _adapter: ExpenditureAdapter? = null
    private var presenter: ExpenditurePresenter? = null
    private var dateFormat: DateTimeFormatter? = null
    private var dateFrom: DateTime = DateTime()
    private var dateTo: DateTime = DateTime()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_expenditure, container, false)
        initControls(view)
        initEvents(view)
        initDatabase()
        return view
    }

    private fun initControls(view: View) {
        view.run {
            val p = fab.layoutParams as CoordinatorLayout.LayoutParams
            p.behavior = ScrollAwareFABBehavior()
            fab.layoutParams = p

            recyclerView!!.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                _adapter = ExpenditureAdapter(object : ExpenditureAdapter.ItemClickListener {
                    override fun onClickListener(type: Expenditure, position: Int) {
                        val intent = Intent(context, UpdateExpenditureActivity::class.java)
                        intent.putExtra("typeId", type.id)
                        startActivity(intent)
                    }

                    override fun onLongClickListener(type: Expenditure, position: Int): Boolean {
                        CustomDialogConfirm.Builder(context)
                                .withTitle(R.string.confirm)
                                .withContent(R.string.deletion)
                                .withIcon(R.drawable.ic_delete)
                                .setOnConfirmClick(object : CustomDialogConfirm.OnConfirmListener {
                                    override fun onResult(dialog: CustomDialogConfirm, result: Boolean) {
                                        presenter!!.delete(type.id!!, position)
                                        dialog.dismiss()
                                    }
                                }).show()
                        return true
                    }
                })
                adapter = _adapter
            }
            dateFormat = LocaleHelper.getDateFormat(context)
            dateFrom = LocalDate().withDayOfWeek(DateTimeConstants.MONDAY).toDateTime(LocalTime())
            dateTo = LocalDate().withDayOfWeek(DateTimeConstants.SUNDAY).toDateTime(LocalTime())
            txtDateFrom.text = dateFormat!!.print(dateFrom)
            txtDateTo.text = dateFormat!!.print(dateTo)
        }
    }

    private fun initEvents(view: View) {
        view.run {
            txtDateFrom.setOnClickListener {
                val dpd = DatePickerDialog.newInstance({ _, year, monthOfYear, dayOfMonth ->
                    dateFrom = DateTime()
                            .withYear(year)
                            .withMonthOfYear(monthOfYear + 1)
                            .withDayOfMonth(dayOfMonth)
                    txtDateFrom.text = dateFormat!!.print(dateFrom)
                    presenter!!.filter(dateFrom, dateTo)
                }, dateFrom.year, dateFrom.monthOfYear - 1, dateFrom.dayOfMonth)
                dpd.setVersion(DatePickerDialog.Version.VERSION_1)
                dpd.show(activity.fragmentManager, "")
            }

            txtDateTo.setOnClickListener {
                val dpd = DatePickerDialog.newInstance({ _, year, monthOfYear, dayOfMonth ->
                    dateTo = DateTime()
                            .withYear(year)
                            .withMonthOfYear(monthOfYear + 1)
                            .withDayOfMonth(dayOfMonth)
                    txtDateTo.text = dateFormat!!.print(dateTo)
                    presenter!!.filter(dateFrom, dateTo)
                }, dateTo.year, dateTo.monthOfYear - 1, dateTo.dayOfMonth)
                dpd.setVersion(DatePickerDialog.Version.VERSION_1)
                dpd.show(activity.fragmentManager, "")
            }

            fab.setOnClickListener {
                val intent = Intent(context, UpdateExpenditureActivity::class.java)
                intent.putExtra("typeId", "")
                startActivity(intent)
            }
        }
    }

    private fun initDatabase() {
        presenter = ExpenditurePresenter(this)
        presenter!!.filter(dateFrom, dateTo)
    }

    override fun onGetListResult(result: Boolean, msg: String, list: List<Expenditure>) {
        _adapter!!.updateList(list.toMutableList())
    }

    override fun onDeleteResult(result: Boolean, msg: String, position: Int) {
        if (result)
            _adapter!!.removeItem(position)
        toast(msg)
    }
}
