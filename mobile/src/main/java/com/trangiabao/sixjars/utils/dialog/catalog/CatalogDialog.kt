package com.trangiabao.sixjars.utils.dialog.catalog

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatDialog
import android.view.inputmethod.EditorInfo
import com.trangiabao.sixjars.R
import kotlinx.android.synthetic.main.dialog_catalog.*

class CatalogDialog(context: Context?,
                    private var type: String?,
                    private var description: String?,
                    private var enum: CatalogEnum?,
                    private var listener: OnConfirmListener?) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_catalog)
        initControls()
        initEvents()
    }

    private fun initControls() {
        txtType.setText(type!!)
        txtType.setSelection(txtType.length())
        txtDescription.setText(description!!)
        if (enum == CatalogEnum.ADD) {
            txtTitle.text = context.getString(R.string.add)
            txtTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add, 0, 0, 0)
        } else {
            txtTitle.text = context.getString(R.string.edit)
            txtTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_edit, 0, 0, 0)
        }
    }

    private fun initEvents() {
        btnOK.setOnClickListener {
            val type = txtType.text.toString().trim()
            val des = txtDescription.text.toString().trim()
            if (type == "")
                txtType.error = context.getString(R.string.required_field)
            else
                listener!!.onResult(this, true, type, des)
        }

        btnCancel.setOnClickListener {
            listener!!.onResult(this, false, "", "")
        }

        txtType.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                txtDescription.setSelection(txtDescription.length())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    interface OnConfirmListener {
        fun onResult(dialog: CatalogDialog, result: Boolean, type: String, description: String)
    }

    class Builder(private var context: Context) {
        private var type: String? = null
        private var description: String? = null
        private var _enum: CatalogEnum? = null
        private var listener: OnConfirmListener? = null

        fun withType(type: String): Builder {
            this.type = type
            return this
        }

        fun withDescription(description: String): Builder {
            this.description = description
            return this
        }

        fun withEnum(_enum: CatalogEnum): Builder {
            this._enum = _enum
            return this
        }

        fun setOnConfirmClick(listener: OnConfirmListener): Builder {
            this.listener = listener
            return this
        }

        fun show() = CatalogDialog(context, type, description, _enum, listener).show()
    }
}
