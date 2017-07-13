package com.trangiabao.sixjars.base.ui.dialog.monthpicker

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.LocaleHelper
import kotlinx.android.synthetic.main.custom_dialog_month_picker.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter

class MonthPickerDialog(context: Context?, private var date: DateTime) : Dialog(context) {

    private var adapter: MonthPickerAdapter? = null
    private var mDialogResult: OnDialogResult? = null
    private var monthFormat: DateTimeFormatter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_month_picker)
        initControls()
        initEvents()
    }

    private fun initControls() {
        monthFormat = LocaleHelper.getMonthFormat(context)
        txtFullMonth.text = monthFormat!!.print(date)
        txtYear.text = date.year.toString()
        rvMonth.setHasFixedSize(true)
        rvMonth.layoutManager = GridLayoutManager(context, 4)
        adapter = MonthPickerAdapter(object : MonthPickerAdapter.OnItemClickListener {
            override fun onMonthSelected(month: Int) {
                date = DateTime().withYear(date.year).withMonthOfYear(month)
                txtFullMonth.text = monthFormat!!.print(date)
            }
        })
        rvMonth.adapter = adapter
    }

    private fun initEvents() {
        btnOK.setOnClickListener(OKListener())
        btnCancel.setOnClickListener { dismiss() }
        btnLeft.setOnClickListener {
            date = DateTime().withYear(date.year - 1).withMonthOfYear(date.monthOfYear)
            txtYear.text = date.year.toString()
            txtFullMonth.text = monthFormat!!.print(date)
        }
        btnRight.setOnClickListener {
            date = DateTime().withYear(date.year + 1).withMonthOfYear(date.monthOfYear)
            txtYear.text = date.year.toString()
            txtFullMonth.text = monthFormat!!.print(date)
        }
    }

    private inner class OKListener : android.view.View.OnClickListener {
        override fun onClick(v: View) {
            if (mDialogResult != null) {
                mDialogResult!!.onGetTime(date)
            }
            dismiss()
        }
    }

    fun setDialogResult(dialogResult: OnDialogResult) {
        mDialogResult = dialogResult
    }

    interface OnDialogResult {
        fun onGetTime(date: DateTime)
    }
}