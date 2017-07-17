package com.trangiabao.sixjars.catalog.revenue_type

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.model.RevenueType
import com.trangiabao.sixjars.ui.ScrollAwareFABBehavior
import com.trangiabao.sixjars.ui.dialog.CustomDialogConfirm
import com.trangiabao.sixjars.ui.dialog.catalog.CatalogDialog
import com.trangiabao.sixjars.ui.dialog.catalog.CatalogEnum
import kotlinx.android.synthetic.main.fragment_type.view.*
import java.util.*

class RevenueTypeFragment : BaseFragment(), RevenueTypeView {

    private var adapter: RevenueTypeAdapter = RevenueTypeAdapter()
    private var presenter: RevenueTypePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_type, container, false)
        initControls(view)
        initEvents(view)
        presenter = RevenueTypePresenter(this)
        presenter!!.getAll()
        return view
    }

    private fun initControls(view: View) {
        view.run {
            val p = fab.layoutParams as CoordinatorLayout.LayoutParams
            p.behavior = ScrollAwareFABBehavior()
            fab.layoutParams = p

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }

    private fun initEvents(view: View) {
        adapter.setOnItemClickListener(object : RevenueTypeAdapter.ItemClickListener {
            override fun onClickListener(obj: RevenueType, position: Int) {
                CatalogDialog.Builder(context)
                        .withType(obj.type!!)
                        .withDescription(obj.description!!)
                        .withEnum(CatalogEnum.EDIT)
                        .setOnConfirmClick(object : CatalogDialog.OnConfirmListener {
                            override fun onResult(dialog: CatalogDialog, result: Boolean, type: String, description: String) {
                                if (result) {
                                    val revenueType = RevenueType()
                                    revenueType.id = obj.id
                                    revenueType.type = type
                                    revenueType.description = description
                                    presenter!!.update(revenueType)
                                }
                                dialog.dismiss()
                            }
                        })
                        .show()
            }

            override fun onLongClickListener(type: RevenueType, position: Int): Boolean {
                CustomDialogConfirm.Builder(context)
                        .withTitle(R.string.confirm)
                        .withContent(R.string.deletion)
                        .withIcon(R.drawable.ic_delete)
                        .setOnConfirmClick(object : CustomDialogConfirm.OnConfirmListener {
                            override fun onResult(dialog: CustomDialogConfirm, result: Boolean) {
                                if (result)
                                    presenter!!.delete(type.id!!, position)
                                dialog.dismiss()
                            }
                        })
                        .show()
                return true
            }
        })

        view.fab.setOnClickListener {
            CatalogDialog.Builder(context)
                    .withType("")
                    .withDescription("")
                    .withEnum(CatalogEnum.ADD)
                    .setOnConfirmClick(object : CatalogDialog.OnConfirmListener {
                        override fun onResult(dialog: CatalogDialog, result: Boolean, type: String, description: String) {
                            if (result) {
                                val revenueType = RevenueType()
                                revenueType.id = UUID.randomUUID().toString()
                                revenueType.type = type
                                revenueType.description = description
                                presenter!!.update(revenueType)
                            }
                            dialog.dismiss()
                        }
                    }).show()
        }
    }

    override fun onGetListResult(result: Boolean, msg: String, list: List<RevenueType>) {
        val temp = list.toMutableList()
        temp.removeAt(0)
        adapter.updateList(temp)
    }

    override fun onUpdateResult(result: Boolean, msg: String, obj: RevenueType?) {
        if (result && obj != null) {
            adapter.updateItem(obj)
            toastSuccess("Update Success")
        } else
            toastError("Update Error")
    }

    override fun onDeleteResult(result: Boolean, msg: String, position: Int) {
        if (result) {
            adapter.removeItem(position)
            toastSuccess("Item has been remove")
        } else
            toastError("Delete Error")
    }
}