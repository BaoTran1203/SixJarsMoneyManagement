package com.trangiabao.sixjars.modules.m_revenue_update.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Revenue
import com.trangiabao.sixjars.data.model.RevenueType
import com.trangiabao.sixjars.modules.m_revenue_update.presenter.UpdateRevenuePresenter
import com.trangiabao.sixjars.utils.AppConstants
import com.trangiabao.sixjars.utils.base.BaseActivity
import com.trangiabao.sixjars.utils.dialog.CustomDialogList
import com.trangiabao.sixjars.utils.helper.DateTimeHelper
import com.trangiabao.sixjars.utils.helper.ToastHelper
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_update_management.*
import kotlinx.android.synthetic.main.layout_app_bar.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import java.util.*

class UpdateRevenueActivity : BaseActivity(), UpdateRevenueView,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var presenter: UpdateRevenuePresenter? = null
    private var dateFormat: DateTimeFormatter? = null
    private var timeFormat: DateTimeFormatter? = null
    private var curDate = DateTime.now()
    private var typeId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_management)
        presenter = UpdateRevenuePresenter(this)
        presenter!!.createView()
    }

    override fun onInitControls() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        dateFormat = DateTimeHelper.getDateFormat(applicationContext)
        timeFormat = DateTimeHelper.getTimeFormat(applicationContext)
        layoutJar.visibility = View.GONE

        typeId = intent.getStringExtra(AppConstants.SEND_VALUE_NAME_TYPE_ID)
        if (typeId == "") {
            title = getString(R.string.add)
            txtType.tag = null
            txtType.text.clear()
            txtJarName.text.clear()
            txtCurrentAmount.setText("0")
            txtDate.setText(dateFormat!!.print(curDate))
            txtTime.setText(timeFormat!!.print(curDate))
            txtDetail.text.clear()
        } else
            presenter!!.getRevenue(typeId)

    }

    override fun onInitEvents() {
        txtDate.setOnClickListener {
            val dateDialog = DatePickerDialog.newInstance(
                    this@UpdateRevenueActivity,
                    curDate.year,
                    curDate.monthOfYear - 1,
                    curDate.dayOfMonth
            )
            dateDialog.setVersion(DatePickerDialog.Version.VERSION_1)
            dateDialog.show(fragmentManager, AppConstants.LOG_TAG)
        }

        txtTime.setOnClickListener {
            val timeDialog = TimePickerDialog.newInstance(
                    this@UpdateRevenueActivity,
                    curDate.hourOfDay,
                    curDate.minuteOfHour,
                    curDate.secondOfMinute,
                    true
            )
            timeDialog.version = TimePickerDialog.Version.VERSION_1
            timeDialog.show(fragmentManager, AppConstants.LOG_TAG)
        }

        txtType.setOnClickListener { presenter!!.getAllRevenueType() }

        txtCurrentAmount.setOnTouchListener(OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= txtCurrentAmount.right - txtCurrentAmount.compoundDrawables[2].bounds.width()) {
                    txtCurrentAmount.setText("0")
                    return@OnTouchListener true
                }
            }
            false
        })

        txtDetail.setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= txtDetail.right - txtDetail.compoundDrawables[2].bounds.width()) {
                    txtDetail.text.clear()
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        curDate = DateTime().withDate(year, monthOfYear + 1, dayOfMonth)
                .withTime(curDate.hourOfDay, curDate.minuteOfHour, curDate.secondOfMinute, 0)
        txtDate.setText(dateFormat!!.print(curDate))
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        curDate = DateTime().withTime(hourOfDay, minute, second, 0)
                .withDate(curDate.year, curDate.monthOfYear, curDate.dayOfMonth)
        txtTime.setText(timeFormat!!.print(curDate))
    }

    override fun onGetListRevenueTypeSuccessed(list: List<RevenueType>) {
        val map: Map<String, RevenueType> = list.map { it.type!! to it }.toMap()
        CustomDialogList.Builder(this)
                .withTitle(R.string.add)
                .withIcon(R.drawable.ic_add)
                .withMap(map)
                .setOnItemClick(object : CustomDialogList.OnItemClickListener {
                    override fun onClickResult(dialog: CustomDialogList, obj: Any?, text: String?, position: Int) {
                        txtType.tag = obj as RevenueType
                        txtType.setText(text!!)
                        dialog.dismiss()
                    }
                })
                .show()
    }

    override fun onUpdateRevenueSuccessed(msg: Int, revenue: Revenue) {
        ToastHelper.toastSuccess(this, msg)
        finish()
    }

    override fun onGetRevenueSuccessed(revenue: Revenue) {
        title = getString(R.string.edit)
        revenue.run {
            txtType.tag = revenueType
            txtType.setText(revenueType!!.type)
            txtCurrentAmount.setText("${amount!!.toInt()}")
            curDate = DateTime(date)
            txtDate.setText(dateFormat!!.print(curDate))
            txtTime.setText(timeFormat!!.print(curDate))
            txtDetail.setText(detail)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
            }

            R.id.itemSave -> {
                val newRevenue = Revenue()
                newRevenue.date = curDate.toDate()
                newRevenue.amount = txtCurrentAmount.numericValue
                newRevenue.revenueType = txtType.tag as RevenueType?
                newRevenue.detail = txtDetail.text.toString().trim()
                if (typeId != "") {
                    newRevenue.id = typeId
                } else {
                    newRevenue.id = UUID.randomUUID().toString()
                }
                presenter!!.updateRevenue(newRevenue)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onError(msg: Int) {
        ToastHelper.toastError(this, msg)
    }

    override fun onWarning(msg: Int) {
        ToastHelper.toastWarning(this, msg)
    }
}
