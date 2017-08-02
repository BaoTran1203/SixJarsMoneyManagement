package com.trangiabao.sixjars.modules.m_expenditure_update.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Expenditure
import com.trangiabao.sixjars.data.model.ExpenditureType
import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.modules.m_expenditure_update.presenter.UpdateExpenditurePresenter
import com.trangiabao.sixjars.utils.application.AppConstants
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

class UpdateExpenditureActivity : BaseActivity(), UpdateExpenditureView,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var presenter: UpdateExpenditurePresenter? = null
    private var dateFormat: DateTimeFormatter? = null
    private var timeFormat: DateTimeFormatter? = null
    private var curDate = DateTime.now()
    private var typeId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_management)
        presenter = UpdateExpenditurePresenter(this)
        presenter!!.createView()
    }

    override fun onInitControls() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        dateFormat = DateTimeHelper.getDateFormat(applicationContext)
        timeFormat = DateTimeHelper.getTimeFormat(applicationContext)

        typeId = intent.getStringExtra(AppConstants.INTENT_REVENUE_ID)
        if (typeId == "") {
            title = getString(R.string.add)
            txtType.tag = null
            txtJarName.tag = null
            txtType.text.clear()
            txtJarName.text.clear()
            txtCurrentAmount.setText("0")
            txtDate.setText(dateFormat!!.print(curDate))
            txtTime.setText(timeFormat!!.print(curDate))
            txtDetail.text.clear()
        } else
            presenter!!.getExpenditure(typeId)
    }

    override fun onInitEvents() {
        txtDate.setOnClickListener {
            val dateDialog = DatePickerDialog.newInstance(
                    this@UpdateExpenditureActivity,
                    curDate.year,
                    curDate.monthOfYear - 1,
                    curDate.dayOfMonth
            )
            dateDialog.setVersion(DatePickerDialog.Version.VERSION_1)
            dateDialog.show(fragmentManager, AppConstants.LOG_TAG)
        }

        txtTime.setOnClickListener {
            val timeDialog = TimePickerDialog.newInstance(
                    this@UpdateExpenditureActivity,
                    curDate.hourOfDay,
                    curDate.minuteOfHour,
                    curDate.secondOfMinute,
                    true
            )
            timeDialog.version = TimePickerDialog.Version.VERSION_1
            timeDialog.show(fragmentManager, AppConstants.LOG_TAG)
        }

        txtType.setOnClickListener { presenter!!.getAllExpenditureType() }

        txtJarName.setOnClickListener { presenter!!.getAllJar() }

        txtCurrentAmount.setOnTouchListener(View.OnTouchListener { _, event ->
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

    override fun onGetListJarSuccessed(list: List<Jar>) {
        val map: Map<String, Jar> = list.map { it.name!! to it }.toMap()
        CustomDialogList.Builder(this)
                .withTitle(R.string.add)
                .withIcon(R.drawable.ic_add)
                .withMap(map)
                .setOnItemClick(object : CustomDialogList.OnItemClickListener {
                    override fun onClickResult(dialog: CustomDialogList, obj: Any?, text: String?, position: Int) {
                        txtJarName.tag = obj as Jar
                        txtJarName.setText(text!!)
                        dialog.dismiss()
                    }
                })
                .show()
    }

    override fun onGetListExpenditureTypeSuccessed(list: List<ExpenditureType>) {
        val map: Map<String, ExpenditureType> = list.map { it.type!! to it }.toMap()
        CustomDialogList.Builder(this)
                .withTitle(R.string.add)
                .withIcon(R.drawable.ic_add)
                .withMap(map)
                .setOnItemClick(object : CustomDialogList.OnItemClickListener {
                    override fun onClickResult(dialog: CustomDialogList, obj: Any?, text: String?, position: Int) {
                        txtType.tag = obj as ExpenditureType
                        txtType.setText(text!!)
                        dialog.dismiss()
                    }
                })
                .show()
    }

    override fun onUpdateExpenditureSuccessed(msg: Int, expenditure: Expenditure) {
        ToastHelper.toastSuccess(this, msg)
        finish()
    }

    override fun onGetExpenditureSuccessed(expenditure: Expenditure) {
        title = getString(R.string.edit)
        expenditure.run {
            txtType.tag = expenditureType
            txtJarName.tag = jar
            txtType.setText(expenditureType!!.type)
            txtJarName.setText(jar!!.name)
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
                val newExpenditure = Expenditure()
                newExpenditure.date = curDate.toDate()
                newExpenditure.amount = txtCurrentAmount.numericValue
                newExpenditure.expenditureType = txtType.tag as ExpenditureType?
                newExpenditure.jar = txtJarName.tag as Jar?
                newExpenditure.detail = txtDetail.text.toString().trim()
                if (typeId != "") {
                    newExpenditure.id = typeId
                } else {
                    newExpenditure.id = UUID.randomUUID().toString()
                }
                presenter!!.updateExpenditure(newExpenditure)
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
