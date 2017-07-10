package com.trangiabao.sixjars.management.edit_revenue

import android.os.Bundle
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.activity.BaseActivity
import com.trangiabao.sixjars.base.model.Revenue
import com.trangiabao.sixjars.base.model.RevenueType
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.custom_app_bar.*
import java.text.SimpleDateFormat
import java.util.*

class EditRevenueActivity : BaseActivity(), EditRevenueView,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var presenter: EditRevenuePresenter? = null
    private var date: Date = Date()
    private var dateFormat: SimpleDateFormat? = null
    private var timeFormat: SimpleDateFormat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_revenue)
        initControls()
        initEvents()
    }

    private fun initControls() {
        setSupportActionBar(toolbar)
        title = "Edit"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        dateFormat = SimpleDateFormat(getString(R.string.date_format), Locale.US)
        timeFormat = SimpleDateFormat(getString(R.string.time_format), Locale.US)
    }

    private fun initEvents() {

    }

    override fun onGetListRevenueTypeResult(list: List<RevenueType>) {

    }

    override fun onEditRevenueResult(revenue: Revenue?) {

    }

    override fun onGetRevenueResult(revenue: Revenue?) {

    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {

    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {

    }
}
