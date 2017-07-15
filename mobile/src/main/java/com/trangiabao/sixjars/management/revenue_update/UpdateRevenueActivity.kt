package com.trangiabao.sixjars.management.revenue_update

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.base.activity.BaseActivity
import com.trangiabao.sixjars.base.model.Revenue
import com.trangiabao.sixjars.base.model.RevenueType
import com.trangiabao.sixjars.ui.dialog.CustomDialogList
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_update_revenue.*
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
    private var curRevenue: Revenue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_revenue)
        initControls()
        initEvents()
    }

    private fun initControls() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        dateFormat = LocaleHelper.getDateFormat(applicationContext)
        timeFormat = LocaleHelper.getTimeFormat(applicationContext)
        val typeId = intent.getStringExtra("typeId")
        presenter = UpdateRevenuePresenter(this)
        presenter!!.getRevenue(typeId)
    }

    override fun onGetRevenue(result: Boolean, msg: String, revenue: Revenue?) {
        curRevenue = revenue
        if (curRevenue != null) {
            title = getString(R.string.edit)
            curRevenue!!.run {
                txtRevenueType.tag = revenueType
                txtRevenueType.setText(revenueType!!.type)
                txtRevenueAmount.setText("${amount!!.toInt()}")
                curDate = DateTime(date)
                txtDate.setText(dateFormat!!.print(curDate))
                txtTime.setText(timeFormat!!.print(curDate))
                txtDetail.setText(detail)
            }
        } else {
            title = getString(R.string.add)
            txtRevenueType.tag = null
            txtRevenueType.text.clear()
            txtRevenueAmount.setText("0")
            txtDate.setText(dateFormat!!.print(curDate))
            txtTime.setText(timeFormat!!.print(curDate))
            txtDetail.text.clear()
        }
    }

    private fun initEvents() {
        txtDate.setOnClickListener {
            val dateDialog = DatePickerDialog.newInstance(
                    this@UpdateRevenueActivity,
                    curDate.year,
                    curDate.monthOfYear - 1,
                    curDate.dayOfMonth
            )
            dateDialog.setVersion(DatePickerDialog.Version.VERSION_1)
            dateDialog.show(fragmentManager, "")
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
            timeDialog.show(fragmentManager, "")
        }

        txtRevenueType.setOnClickListener { presenter!!.getAllRevenueType() }

        txtRevenueAmount.setOnTouchListener(OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= txtRevenueAmount.right - txtRevenueAmount.compoundDrawables[2].bounds.width()) {
                    txtRevenueAmount.setText("0")
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

    override fun onListRevenueTypeLoaded(result: Boolean, msg: String, list: List<RevenueType>) {
        val map: Map<String, RevenueType> = list.map { it.type!! to it }.toMap()
        CustomDialogList.Builder(this)
                .withTitle(R.string.add)
                .withIcon(R.drawable.ic_add)
                .withMap(map)
                .setOnItemClick(object : CustomDialogList.OnItemClickListener {
                    override fun onClickResult(dialog: CustomDialogList, obj: Any?, text: String?, position: Int) {
                        txtRevenueType.tag = obj as RevenueType
                        txtRevenueType.setText(text!!)
                        dialog.dismiss()
                    }
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
                if (txtRevenueAmount.numericValue < 1.0) {
                    toast("Amout > 0")
                } else if (txtRevenueType.tag == null) {
                    toast("Choose revenue type")
                } else {
                    val newRevenue = Revenue()
                    newRevenue.date = curDate.toDate()
                    newRevenue.amount = txtRevenueAmount.numericValue
                    newRevenue.revenueType = txtRevenueType.tag as RevenueType?
                    newRevenue.detail = txtDetail.text.toString().trim()
                    if (curRevenue != null) {
                        newRevenue.id = curRevenue!!.id
                    } else {
                        newRevenue.id = UUID.randomUUID().toString()
                    }
                    presenter!!.updateRevenue(newRevenue)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onUpdateRevenueResult(result: Boolean, msg: String, revenue: Revenue?) {
        if (revenue != null)
            finish()
        else {
            toast("Fail")
        }
    }
}
