package com.trangiabao.sixjars.modules.m_expenditure.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.modules.m_expenditure.adapter.ExpenditureAdapter
import com.trangiabao.sixjars.modules.m_expenditure.presenter.ExpenditurePresenter
import com.trangiabao.sixjars.modules.m_expenditure_update.view.UpdateExpenditureActivity
import com.trangiabao.sixjars.utils.application.AppConstants
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.component.ScrollAwareFABBehavior
import com.trangiabao.sixjars.utils.dialog.CustomDialogConfirm
import com.trangiabao.sixjars.utils.helper.DateTimeHelper
import com.trangiabao.sixjars.utils.helper.ToastHelper
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_expenditure.view.*
import kotlinx.android.synthetic.main.layout_date_filter.view.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter

class ExpenditureFragment : BaseFragment(), ExpenditureView {

    private var mView: View? = null
    private var _adapter: ExpenditureAdapter? = null
    private var presenter: ExpenditurePresenter? = null
    private var dateFormat: DateTimeFormatter? = null
    private var dateFrom: DateTime = DateTime()
    private var dateTo: DateTime = DateTime()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_expenditure, container, false)
        presenter = ExpenditurePresenter(this)
        presenter!!.createView()
        presenter!!.filter(dateFrom, dateTo)
        return mView
    }

    override fun onInitControls() {
        mView!!.run {
            val p = fab.layoutParams as CoordinatorLayout.LayoutParams
            p.behavior = ScrollAwareFABBehavior()
            fab.layoutParams = p

            recyclerView!!.run {
                _adapter = ExpenditureAdapter()
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = _adapter
            }
            dateFormat = DateTimeHelper.getDateFormat(context)
            dateFrom = DateTimeHelper.getDateFrom()
            dateTo = DateTimeHelper.getDateTo()
            txtDateFrom.text = DateTimeHelper.printDate(dateFormat!!, dateFrom)
            txtDateTo.text = DateTimeHelper.printDate(dateFormat!!, dateTo)
        }
    }

    override fun onInitEvents() {
        _adapter!!.setOnItemClickListener(object : ExpenditureAdapter.ItemClickListener {
            override fun onClickListener(type: Expenditure, position: Int) {
                val intent = Intent(context, UpdateExpenditureActivity::class.java)
                intent.putExtra(AppConstants.INTENT_REVENUE_ID, type.id)
                startActivity(intent)
            }

            override fun onLongClickListener(type: Expenditure, position: Int): Boolean {
                CustomDialogConfirm.Builder(context)
                        .withTitle(R.string.confirm)
                        .withContent(R.string.deletion)
                        .withIcon(R.drawable.ic_delete)
                        .setOnConfirmClick(object : CustomDialogConfirm.OnConfirmListener {
                            override fun onResult(dialog: CustomDialogConfirm, result: Boolean) {
                                if (result)
                                    presenter!!.delete(type.id!!, position)
                                dialog.dismiss()
                            }
                        }).show()
                return true
            }
        })

        mView!!.run {
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
                intent.putExtra(AppConstants.INTENT_REVENUE_ID, "")
                startActivity(intent)
            }
        }
    }

    override fun onGetListSuccessed(list: List<Expenditure>) {
        _adapter!!.updateList(list.toMutableList())
    }

    override fun onDeleteSuccessed(msg: Int, position: Int) {
        _adapter!!.removeItem(position)
        ToastHelper.toastSuccess(context, msg)
    }

    override fun onError(msg: Int) {
        ToastHelper.toastError(context, msg)
    }

    override fun onWarning(msg: Int) {
        ToastHelper.toastWarning(context, msg)
    }
}
