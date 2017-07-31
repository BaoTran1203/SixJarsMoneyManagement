package com.trangiabao.sixjars.utils.component.dialog

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatDialog
import com.trangiabao.sixjars.R
import kotlinx.android.synthetic.main.dialog_confirm.*

class CustomDialogConfirm(context: Context,
                          private var title: Int?,
                          private var content: Int?,
                          private var icon: Int?,
                          private var listner: OnConfirmListener?) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_confirm)
        txtTitle.text = context.getString(title!!)
        txtContent.text = context.getString(content!!)
        txtTitle.setCompoundDrawablesWithIntrinsicBounds(icon!!, 0, 0, 0)
        btnOK.setOnClickListener { listner!!.onResult(this, true) }
        btnCancel.setOnClickListener { listner!!.onResult(this, false) }
    }

    interface OnConfirmListener {
        fun onResult(dialog: CustomDialogConfirm, result: Boolean)
    }

    class Builder(private var context: Context) {

        private var listner: OnConfirmListener? = null
        private var title: Int? = null
        private var content: Int? = null
        private var icon: Int? = null

        fun withTitle(title: Int): Builder {
            this.title = title
            return this
        }

        fun withContent(content: Int): Builder {
            this.content = content
            return this
        }

        fun withIcon(icon: Int): Builder {
            this.icon = icon
            return this
        }

        fun setOnConfirmClick(listner: OnConfirmListener): Builder {
            this.listner = listner
            return this
        }

        fun show() = CustomDialogConfirm(context, title, content, icon, listner).show()
    }
}