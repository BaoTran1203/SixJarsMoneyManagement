package com.trangiabao.sixjars.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.adapter.RevenueTypeAdapter
import com.trangiabao.sixjars.model.RevenueType
import com.trangiabao.sixjars.presenter.RevenueTypePresenter
import com.trangiabao.sixjars.view.RevenueTypeView
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.fragment_type.view.*
import java.util.*

class RevenueTypeFragment : Fragment(), RevenueTypeView {

    private var adapter: RevenueTypeAdapter? = null
    private var presenter: RevenueTypePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_type, container, false)
        setupUI(view)
        presenter = RevenueTypePresenter(context, this)
        presenter!!.getAll()
        return view
    }

    private fun setupUI(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RevenueTypeAdapter(object : RevenueTypeAdapter.ItemClickListener {
            override fun onClickListener(type: RevenueType, position: Int) {
                openEditDialog(type)
            }

            override fun onLongClickListener(type: RevenueType, position: Int): Boolean {
                AlertDialog.Builder(context)
                        .setTitle("")
                        .setMessage("")
                        .setIcon(R.drawable.ic_info)
                        .setPositiveButton(getString(R.string.confirm)) { dialog, _ ->
                            presenter!!.delete(type.id)
                            adapter!!.removeItem(position)
                            dialog.dismiss()
                        }
                        .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                return true
            }
        })
        view.recyclerView.adapter = adapter
        view.fab.setOnClickListener {
            openAddDialog()
        }
    }

    @SuppressLint("InflateParams")
    private fun openAddDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog, null)
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setView(dialogView)
        alertDialog.setTitle(getString(R.string.add))
        alertDialog.setMessage("Add bla bla bla")
        alertDialog.setIcon(R.drawable.ic_add)
        alertDialog.setPositiveButton(getString(R.string.confirm)) { dialog, _ ->
            val type: RevenueType = RevenueType()
            type.id = UUID.randomUUID().toString()
            type.type = dialogView.txtType.text.toString().trim()
            type.description = dialogView.txtDescription.text.toString().trim()
            if (type.type == "")
                dialogView.txtType.error = "Fill plz"
            else {
                presenter!!.add(type)
                dialog.dismiss()
            }
        }
        alertDialog.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    @SuppressLint("InflateParams")
    private fun openEditDialog(obj: RevenueType) {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog, null)
        dialogView.txtType.setText(obj.type, TextView.BufferType.EDITABLE)
        dialogView.txtDescription.setText(obj.description, TextView.BufferType.EDITABLE)

        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setView(dialogView)
        alertDialog.setTitle(getString(R.string.edit))
        alertDialog.setMessage("Edit bla bla bla")
        alertDialog.setIcon(R.drawable.ic_estimate)
        alertDialog.setPositiveButton(getString(R.string.confirm)) { dialog, _ ->
            obj.type = dialogView.txtType.text.toString().trim()
            obj.description = dialogView.txtDescription.text.toString().trim()
            if (obj.type == "")
                dialogView.txtType.error = "Fill plz"
            else {
                presenter!!.update(obj)
                dialog.dismiss()
            }
        }
        alertDialog.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    override fun onGetListResult(list: MutableList<RevenueType>) {
        if (list.size > 0) {
            Toast.makeText(context, "Load list thanh cong", Toast.LENGTH_SHORT).show()
            adapter!!.updateList(list)
        } else {
            Toast.makeText(context, "Load list that bai", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAddResult(obj: RevenueType?) {
        if (obj != null) {
            Toast.makeText(context, "Add thanh cong", Toast.LENGTH_SHORT).show()
            adapter!!.addItem(obj)
        } else {
            Toast.makeText(context, "Add that bai", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUpdateResult(obj: RevenueType?) {
        if (obj != null) {
            Toast.makeText(context, "Update thanh cong", Toast.LENGTH_SHORT).show()
            adapter!!.updateItem(obj)
        } else {
            Toast.makeText(context, "Update that bai", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDeleteResult(result: Boolean) {
        if (result) {
            Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onGetObjectResult(obj: RevenueType?) {
        // TODO ("Do nothing")
    }
}
