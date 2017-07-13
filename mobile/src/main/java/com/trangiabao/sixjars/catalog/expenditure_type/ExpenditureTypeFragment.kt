package com.trangiabao.sixjars.catalog.expenditure_type

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.model.ExpenditureType
import com.trangiabao.sixjars.base.ui.dialog.catalog.CatalogDialog
import com.trangiabao.sixjars.base.ui.dialog.catalog.CatalogEnum
import com.trangiabao.sixjars.catalog.expenditure_type.ExpenditureTypeAdapter.ItemClickListener
import kotlinx.android.synthetic.main.fragment_type.view.*
import java.util.*

class ExpenditureTypeFragment : BaseFragment(), ExpenditureTypeView {

    private var adapter: ExpenditureTypeAdapter? = null
    private var presenter: ExpenditureTypePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_type, container, false)
        initControls(view)
        initEvents(view)
        initDatabase()
        return view
    }

    private fun initControls(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ExpenditureTypeAdapter(object : ItemClickListener {
            override fun onClickListener(type: ExpenditureType, position: Int) {
                createEditDialog(type)
            }

            override fun onLongClickListener(type: ExpenditureType, position: Int): Boolean {
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
        presenter = ExpenditureTypePresenter(this)
        presenter!!.getAll()
    }

    @SuppressLint("InflateParams")
    private fun createAddDialog() {
        val dialog = CatalogDialog(context, "", "", CatalogEnum.ADD)
        dialog.setDialogResult(object : CatalogDialog.OnDialogResult {
            override fun onResult(type: String, description: String) {
                val expenditureType = ExpenditureType()
                expenditureType.id = UUID.randomUUID().toString()
                expenditureType.type = type
                expenditureType.description = description
                presenter!!.update(expenditureType)
            }
        })
        dialog.show()
    }

    @SuppressLint("InflateParams")
    private fun createEditDialog(obj: ExpenditureType) {
        val dialog = CatalogDialog(context, obj.type!!, obj.description!!, CatalogEnum.EDIT)
        dialog.setDialogResult(object : CatalogDialog.OnDialogResult {
            override fun onResult(type: String, description: String) {
                val expenditureType = ExpenditureType()
                expenditureType.id = obj.id
                expenditureType.type = type
                expenditureType.description = description
                presenter!!.update(expenditureType)
            }
        })
        dialog.show()
    }

    override fun onGetListResult(list: List<ExpenditureType>) {
        val temp = list.toMutableList()
        temp.removeAt(0)
        adapter!!.updateList(temp)
    }

    override fun onUpdateResult(obj: ExpenditureType?) {
        if (obj != null) {
            adapter!!.updateItem(obj)
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
