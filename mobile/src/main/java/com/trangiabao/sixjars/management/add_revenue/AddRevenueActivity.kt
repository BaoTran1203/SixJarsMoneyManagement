package com.trangiabao.sixjars.management.add_revenue

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.afollestad.materialdialogs.MaterialDialog
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.activity.BaseActivity
import com.trangiabao.sixjars.base.model.Revenue
import com.trangiabao.sixjars.base.model.RevenueType
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_add_revenue.*
import kotlinx.android.synthetic.main.custom_app_bar.*
import java.text.SimpleDateFormat
import java.util.*

class AddRevenueActivity : BaseActivity(), AddRevenueView,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var presenter: AddRevenuePresenter? = null
    private var date: Date = Date()
    private var dateFormat: SimpleDateFormat? = null
    private var timeFormat: SimpleDateFormat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_revenue)
        initControls()
        initEvents()
    }

    private fun initControls() {
        setSupportActionBar(toolbar)
        title = "Add" // TODO
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        dateFormat = SimpleDateFormat(getString(R.string.date_format), Locale.US)
        timeFormat = SimpleDateFormat(getString(R.string.time_format), Locale.US)
        txtDate.setText(dateFormat!!.format(date))
        txtTime.setText(timeFormat!!.format(date))
        txtRevenueAmount.setText("0")
        txtRevenueType.tag = null
        presenter = AddRevenuePresenter(this)
    }

    private fun initEvents() {
        txtDate.setOnClickListener {
            val cal = Calendar.getInstance()
            cal.time = date
            val dateDialog = DatePickerDialog.newInstance(
                    this@AddRevenueActivity,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            )
            dateDialog.show(fragmentManager, "Datepickerdialog")
            dateDialog.setVersion(DatePickerDialog.Version.VERSION_1)
            dateDialog.maxDate = Calendar.getInstance()
        }

        txtTime.setOnClickListener {
            val cal = Calendar.getInstance()
            cal.time = date
            val timeDialog = TimePickerDialog.newInstance(
                    this@AddRevenueActivity,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    cal.get(Calendar.SECOND),
                    true
            )
            timeDialog.show(fragmentManager, "Timepickerdialog")
            timeDialog.version = TimePickerDialog.Version.VERSION_1
        }

        txtRevenueType.setOnClickListener { presenter!!.getAllRevenueType() }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, monthOfYear)
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        date = cal.time
        txtDate.setText(dateFormat!!.format(date))
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
        cal.set(Calendar.MINUTE, minute)
        cal.set(Calendar.SECOND, second)
        cal.set(Calendar.MILLISECOND, 0)
        date = cal.time
        txtTime.setText(timeFormat!!.format(date))
    }

    override fun onListRevenueTypeLoaded(list: List<RevenueType>) {
        val lstTypes: MutableList<String> = mutableListOf()
        list.mapTo(lstTypes) { it.type!! }
        MaterialDialog.Builder(this)
                .title("Title")
                .items(lstTypes)
                .itemsCallback({ dialog, _, which, text ->
                    txtRevenueType.tag = list[which]
                    txtRevenueType.setText(text)
                    dialog.dismiss()
                })
                .show()
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
                val id = UUID.randomUUID().toString()
                val amount = txtRevenueAmount.numericValue
                val revenueType = txtRevenueType.tag as RevenueType?
                val detail = txtDetail.text.toString().trim()
                if (amount < 1.0) {
                    toast("Amout > 0")
                } else if (revenueType == null) {
                    toast("Choose revenue type")
                } else {
                    val revenue: Revenue = Revenue()
                    revenue.id = id
                    revenue.date = date
                    revenue.amount = amount
                    revenue.revenueType = revenueType
                    revenue.detail = detail
                    presenter!!.addRevenue(revenue)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAddRevenueResult(revenue: Revenue?) {
        if (revenue != null)
            finish()
        else {
            toast("Fail")
        }
    }
}
