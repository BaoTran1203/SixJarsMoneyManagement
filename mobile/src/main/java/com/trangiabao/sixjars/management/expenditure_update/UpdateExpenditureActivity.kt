package com.trangiabao.sixjars.management.expenditure_update

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.base.activity.BaseActivity
import com.trangiabao.sixjars.base.model.Expenditure
import com.trangiabao.sixjars.base.model.ExpenditureType
import com.trangiabao.sixjars.base.model.Jar
import com.trangiabao.sixjars.ui.dialog.CustomDialogList
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_update_expenditure.*
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
    private var curExpenditure: Expenditure? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_expenditure)
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
        presenter = UpdateExpenditurePresenter(this)
        presenter!!.getExpenditure(typeId)
    }

    override fun onGetExpenditure(result: Boolean, msg: String, expenditure: Expenditure?) {
        curExpenditure = expenditure
        if (curExpenditure != null) {
            title = getString(R.string.edit)
            curExpenditure!!.run {
                txtExpenditureType.tag = expenditureType
                txtJarName.tag = jar
                txtExpenditureType.setText(expenditureType!!.type)
                txtJarName.setText(jar!!.name)
                txtExpenditureAmount.setText("${amount!!.toInt()}")
                curDate = DateTime(date)
                txtDate.setText(dateFormat!!.print(curDate))
                txtTime.setText(timeFormat!!.print(curDate))
                txtDetail.setText(detail)
            }
        } else {
            title = getString(R.string.add)
            txtExpenditureType.tag = null
            txtJarName.tag = null
            txtExpenditureType.text.clear()
            txtJarName.text.clear()
            txtExpenditureAmount.setText("0")
            txtDate.setText(dateFormat!!.print(curDate))
            txtTime.setText(timeFormat!!.print(curDate))
            txtDetail.text.clear()
        }
    }

    private fun initEvents() {
        txtDate.setOnClickListener {
            val dateDialog = DatePickerDialog.newInstance(
                    this@UpdateExpenditureActivity,
                    curDate.year,
                    curDate.monthOfYear - 1,
                    curDate.dayOfMonth
            )
            dateDialog.setVersion(DatePickerDialog.Version.VERSION_1)
            dateDialog.show(fragmentManager, "Datepickerdialog")
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
            timeDialog.show(fragmentManager, "Timepickerdialog")
        }

        txtExpenditureType.setOnClickListener { presenter!!.getAllExpenditureType() }

        txtJarName.setOnClickListener { presenter!!.getAllJar() }

        txtExpenditureAmount.setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= txtExpenditureAmount.right - txtExpenditureAmount.compoundDrawables[2].bounds.width()) {
                    txtExpenditureAmount.setText("0")
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

    override fun onListExpenditureTypeLoaded(result: Boolean, msg: String, list: List<ExpenditureType>) {
        val map: Map<String, ExpenditureType> = list.map { it.type!! to it }.toMap()
        CustomDialogList.Builder(this)
                .withTitle(R.string.add)
                .withIcon(R.drawable.ic_add)
                .withMap(map)
                .setOnItemClick(object : CustomDialogList.OnItemClickListener {
                    override fun onClickResult(dialog: CustomDialogList, obj: Any?, text: String?, position: Int) {
                        txtExpenditureType.tag = obj as ExpenditureType
                        txtExpenditureType.setText(text!!)
                        dialog.dismiss()
                    }
                })
                .show()
    }

    override fun onListJarLoaded(result: Boolean, msg: String, list: List<Jar>) {
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
                if (txtExpenditureAmount.numericValue < 1.0) {
                    //toast("Amout > 0")
                } else if (txtExpenditureType.tag == null) {
                    //toast("Choose Expenditure type")
                } else if (txtJarName.tag == null) {
                    // toast("Choose Jar type")
                } else {
                    val newExpenditure = Expenditure()
                    newExpenditure.date = curDate.toDate()
                    newExpenditure.amount = txtExpenditureAmount.numericValue
                    newExpenditure.expenditureType = txtExpenditureType.tag as ExpenditureType?
                    newExpenditure.jar = txtJarName.tag as Jar?
                    newExpenditure.detail = txtDetail.text.toString().trim()
                    if (curExpenditure != null) {
                        newExpenditure.id = curExpenditure!!.id
                    } else {
                        newExpenditure.id = UUID.randomUUID().toString()
                    }
                    presenter!!.updateExpenditure(newExpenditure)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onUpdateExpenditureResult(result: Boolean, msg: String, expenditure: Expenditure?) {
        if (result && expenditure != null) {
            toastSuccess("Update Success")
            finish()
        } else
            toastError("Update Error")
    }
}
