package com.trangiabao.sixjars.catalog.revenue

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.catalog.revenue.RevenueTypeAdapter.ItemClickListener
import com.trangiabao.sixjars.model.RevenueType
import kotlinx.android.synthetic.main.custom_dialog_catalog.view.*
import kotlinx.android.synthetic.main.fragment_type.view.*
import java.util.*

class RevenueTypeFragment : BaseFragment(), RevenueTypeView {

    private var adapter: RevenueTypeAdapter? = null
    private var presenter: RevenueTypePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_type, container, false)
        initControls(view)
        initEvents(view)
        initDatabase()
        return view
    }

    private fun initControls(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RevenueTypeAdapter(object : ItemClickListener {
            override fun onClickListener(type: RevenueType, position: Int) {
                createEditDialog(type)
            }

            override fun onLongClickListener(type: RevenueType, position: Int): Boolean {
                AlertDialog.Builder(context)
                        .setTitle(R.string.confirm)
                        .setMessage(R.string.deletion)
                        .setIcon(R.drawable.ic_delete)
                        .setNegativeButton(R.string.cancel, null)
                        .setPositiveButton(R.string.confirm) { dialog, _ ->
                            presenter!!.delete(type.id)
                            adapter!!.removeItem(position)
                            dialog.dismiss()
                        }.show()
                return true
            }
        })
        view.recyclerView.adapter = adapter
    }

    private fun initEvents(view: View) {
        view.fab.setOnClickListener { createAddDialog() }
    }

    private fun initDatabase() {
        presenter = RevenueTypePresenter(this)
        presenter!!.getAll()
    }

    @SuppressLint("InflateParams")
    private fun createAddDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.custom_dialog_catalog, null)
        val alertDialog = AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle(R.string.add)
                .setIcon(R.drawable.ic_add)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.confirm, null)
                .create()
        alertDialog.setOnShowListener { dialog ->
            val btn = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
            btn.setOnClickListener {
                if (dialogView.txtType.text.toString().trim() == "")
                    dialogView.txtType.error = getString(R.string.required_field)
                else {
                    val id = UUID.randomUUID().toString()
                    val type = dialogView.txtType.text.toString().trim()
                    val description = dialogView.txtDescription.text.toString().trim()
                    val revenueType = RevenueType(id, type, description)
                    presenter!!.add(revenueType)
                    dialog.dismiss()
                }
            }
        }
        alertDialog.show()
    }

    @SuppressLint("InflateParams")
    private fun createEditDialog(obj: RevenueType) {
        val dialogView: View = layoutInflater.inflate(R.layout.custom_dialog_catalog, null)
        dialogView.txtType.setText(obj.type, TextView.BufferType.EDITABLE)
        dialogView.txtDescription.setText(obj.description, TextView.BufferType.EDITABLE)
        val alertDialog = AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle(R.string.edit)
                .setIcon(R.drawable.ic_estimate)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.confirm, null)
                .create()
        alertDialog.setOnShowListener { dialog ->
            dialogView.txtType.setSelection(dialogView.txtType.text.length)
            val btn = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
            btn.setOnClickListener {
                if (dialogView.txtType.text.toString().trim() == "")
                    dialogView.txtType.error = getString(R.string.required_field)
                else {
                    val id = obj.id
                    val type = dialogView.txtType.text.toString().trim()
                    val description = dialogView.txtDescription.text.toString().trim()
                    val revenueType = RevenueType(id, type, description)
                    presenter!!.update(revenueType)
                    dialog.dismiss()
                }
            }
        }
        alertDialog.show()
    }

    override fun onGetListResult(list: List<RevenueType>) {
        val temp = list.toMutableList()
        temp.filter { it.type == "Unknown" }.forEach { temp.remove(it) }
        adapter!!.updateList(temp)
    }

    override fun onAddResult(obj: RevenueType?) {
        if (obj != null) {
            adapter!!.addItem(obj)
            toast(R.string.item_added)
        } else {
            toast("Add that bai")
        }
    }

    override fun onUpdateResult(obj: RevenueType?) {
        if (obj != null) {
            adapter!!.updateItem(obj)
            toast(R.string.update_successful)
        } else {
            toast("Update that bai")
        }
    }

    override fun onDeleteResult(result: Boolean) {
        if (result) {
            toast(R.string.item_deleted)
        } else {
            toast("Xoa that bai")
        }
    }
}
