package com.trangiabao.sixjars.utils.component.dialog

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatDialog
import android.widget.ArrayAdapter
import com.trangiabao.sixjars.R
import kotlinx.android.synthetic.main.dialog_list.*

class CustomDialogList(context: Context,
                       private var title: Int?,
                       private var icon: Int?,
                       private var map: Map<String, Any>,
                       private var listner: OnItemClickListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_list)
        txtTitle.text = context.getString(title!!)
        txtTitle.setCompoundDrawablesWithIntrinsicBounds(icon!!, 0, 0, 0)
        val listString: MutableList<String> = mutableListOf()
        val listObject: MutableList<Any> = mutableListOf()
        for ((k, v) in map) {
            listString.add(k)
            listObject.add(v)
        }
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, listString)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, i, _ ->
            listner.onClickResult(this, listObject[i], listString[i], i)
        }
    }

    interface OnItemClickListener {
        fun onClickResult(dialog: CustomDialogList, obj: Any?, text: String?, position: Int)
    }

    class Builder(private var context: Context) {
        private var listner: OnItemClickListener? = null
        private var title: Int? = null
        private var icon: Int? = null
        private var map: Map<String, Any> = mapOf()

        fun withTitle(title: Int): Builder {
            this.title = title
            return this
        }

        fun withIcon(icon: Int): Builder {
            this.icon = icon
            return this
        }

        fun withMap(map: Map<String, Any>): Builder {
            this.map = map
            return this
        }

        fun setOnItemClick(listner: OnItemClickListener): Builder {
            this.listner = listner
            return this
        }

        fun show() = CustomDialogList(context, title, icon, map, listner!!).show()
    }
}