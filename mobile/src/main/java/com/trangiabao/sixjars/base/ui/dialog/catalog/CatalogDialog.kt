package com.trangiabao.sixjars.base.ui.dialog.catalog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.trangiabao.sixjars.R
import kotlinx.android.synthetic.main.custom_dialog_catalog.*

class CatalogDialog(context: Context?,
                    private var type: String,
                    private var description: String,
                    private var enum: CatalogEnum) : Dialog(context) {

    private var mDialogResult: OnDialogResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_catalog)
        initControls()
        initEvents()
    }

    private fun initControls() {
        txtType.setText(type)
        txtType.setSelection(txtType.length())
        txtDescription.setText(description)
        if (enum == CatalogEnum.ADD) {
            txtTitle.text = context.getString(R.string.add)
            txtTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add, 0, 0, 0)
        } else {
            txtTitle.text = context.getString(R.string.edit)
            txtTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit, 0, 0, 0)
        }
    }

    private fun initEvents() {
        btnOK.setOnClickListener(OKListener())
        btnCancel.setOnClickListener { dismiss() }
        txtType.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                txtDescription.setSelection(txtDescription.length())
            }
            true
        }
    }

    private inner class OKListener : android.view.View.OnClickListener {
        override fun onClick(v: View) {
            if (mDialogResult != null) {
                val type = txtType.text.toString().trim()
                val des = txtDescription.text.toString().trim()
                if (type == "")
                    txtType.error = context.getString(R.string.required_field)
                else {
                    mDialogResult!!.onResult(type, des)
                    dismiss()
                }
            }
        }
    }

    fun setDialogResult(dialogResult: OnDialogResult) {
        mDialogResult = dialogResult
    }

    interface OnDialogResult {
        fun onResult(type: String, description: String)
    }
}