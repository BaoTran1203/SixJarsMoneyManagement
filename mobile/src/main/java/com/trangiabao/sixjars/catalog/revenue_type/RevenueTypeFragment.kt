package com.trangiabao.sixjars.catalog.revenue_type

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.model.RevenueType
import com.trangiabao.sixjars.base.ui.dialog.catalog.CatalogDialog
import com.trangiabao.sixjars.base.ui.dialog.catalog.CatalogEnum
import com.trangiabao.sixjars.catalog.revenue_type.RevenueTypeAdapter.ItemClickListener
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
                            presenter!!.delete(type.id!!)
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
        val dialog = CatalogDialog(context, "", "", CatalogEnum.ADD)
        dialog.setDialogResult(object : CatalogDialog.OnDialogResult {
            override fun onResult(type: String, description: String) {
                val revenueType = RevenueType()
                revenueType.id = UUID.randomUUID().toString()
                revenueType.type = type
                revenueType.description = description
                presenter!!.update(revenueType)
            }
        })
        dialog.show()
    }

    @SuppressLint("InflateParams")
    private fun createEditDialog(obj: RevenueType) {
        val dialog = CatalogDialog(context, obj.type!!, obj.description!!, CatalogEnum.EDIT)
        dialog.setDialogResult(object : CatalogDialog.OnDialogResult {
            override fun onResult(type: String, description: String) {
                val revenueType = RevenueType()
                revenueType.id = obj.id
                revenueType.type = type
                revenueType.description = description
                presenter!!.update(revenueType)
            }
        })
        dialog.show()
    }

    override fun onGetListResult(result: Boolean, msg: String, list: List<RevenueType>) {
        val temp = list.toMutableList()
        temp.removeAt(0)
        adapter!!.updateList(temp)
    }

    override fun onUpdateResult(result: Boolean, msg: String, obj: RevenueType?) {
        if (obj != null) {
            adapter!!.updateItem(obj)
        } else {
            toast("Update that bai")
        }
    }

    override fun onDeleteResult(result: Boolean, msg: String) {
        toast(msg)
    }
}
