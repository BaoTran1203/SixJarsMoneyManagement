package com.trangiabao.sixjars.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.adapter.ExpenditureTypeAdapter
import com.trangiabao.sixjars.adapter.ExpenditureTypeAdapter.ItemClickListener
import com.trangiabao.sixjars.model.ExpenditureType
import com.trangiabao.sixjars.presenter.ExpenditureTypePresenter
import com.trangiabao.sixjars.system.BaseFragment
import com.trangiabao.sixjars.view.ExpenditureTypeView
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.fragment_type.view.*
import java.util.*

class ExpenditureTypeFragment : BaseFragment(), ExpenditureTypeView {

    private var adapter: ExpenditureTypeAdapter? = null
    private var presenter: ExpenditureTypePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_type, container, false)
        setupUI(view)
        presenter = ExpenditureTypePresenter(context, this)
        presenter!!.getAll()
        return view
    }

    private fun setupUI(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ExpenditureTypeAdapter(object : ItemClickListener {
            override fun onClickListener(type: ExpenditureType, position: Int) {
                openEditDialog(type).show()
            }

            override fun onLongClickListener(type: ExpenditureType, position: Int): Boolean {
                AlertDialog.Builder(context)
                        .setTitle("")
                        .setMessage("")
                        .setIcon(R.drawable.ic_info)
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
        view.fab.setOnClickListener { openAddDialog().show() }
    }

    @SuppressLint("InflateParams")
    private fun openAddDialog(): AlertDialog {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog, null)
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
                val type: ExpenditureType = ExpenditureType()
                type.id = UUID.randomUUID().toString()
                type.type = dialogView.txtType.text.toString().trim()
                type.description = dialogView.txtDescription.text.toString().trim()
                if (type.type == "")
                    dialogView.txtType.error = getString(R.string.required_field)
                else {
                    presenter!!.add(type)
                    dialog.dismiss()
                }
            }
        }
        return alertDialog
    }

    @SuppressLint("InflateParams")
    private fun openEditDialog(obj: ExpenditureType): AlertDialog {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog, null)
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
            obj.type = dialogView.txtType.text.toString().trim()
            obj.description = dialogView.txtDescription.text.toString().trim()
            if (obj.type == "")
                dialogView.txtType.error = "Fill plz"
            else {
                presenter!!.update(obj)
                dialog.dismiss()
            }
        }
        return alertDialog
    }

    override fun onGetListResult(list: MutableList<ExpenditureType>) {
        adapter!!.updateList(list)
    }

    override fun onAddResult(obj: ExpenditureType?) {
        if (obj != null) {
            adapter!!.addItem(obj)
            toast(R.string.item_added)
        } else {
            toast("Add that bai")
        }
    }

    override fun onUpdateResult(obj: ExpenditureType?) {
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

    override fun onGetObjectResult(obj: ExpenditureType?) {
        // TODO ("Do nothing")
    }
}
