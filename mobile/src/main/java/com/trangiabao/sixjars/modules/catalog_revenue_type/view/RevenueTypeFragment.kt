package com.trangiabao.sixjars.modules.catalog_revenue_type.view

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.RevenueType
import com.trangiabao.sixjars.modules.catalog_revenue_type.adapter.RevenueTypeAdapter
import com.trangiabao.sixjars.modules.catalog_revenue_type.presenter.RevenueTypePresenter
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.component.ScrollAwareFABBehavior
import com.trangiabao.sixjars.utils.component.dialog.CustomDialogConfirm
import com.trangiabao.sixjars.utils.component.dialog.catalog.CatalogDialog
import com.trangiabao.sixjars.utils.component.dialog.catalog.CatalogEnum
import com.trangiabao.sixjars.utils.component.toast.ToastHelper
import kotlinx.android.synthetic.main.fragment_type.view.*
import java.util.*

class RevenueTypeFragment : BaseFragment(), RevenueTypeView {

    private var mView: View? = null
    private var adapter: RevenueTypeAdapter = RevenueTypeAdapter()
    private var presenter: RevenueTypePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_type, container, false)
        presenter = RevenueTypePresenter(this)
        presenter!!.createView()
        presenter!!.getAll()
        return mView
    }

    override fun onInitControls() {
        mView!!.run {
            val p = fab.layoutParams as CoordinatorLayout.LayoutParams
            p.behavior = ScrollAwareFABBehavior()
            fab.layoutParams = p

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }

    override fun onInitEvents() {
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

        mView!!.fab.setOnClickListener {
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
            ToastHelper(context).toastSuccess("Update Success")
        } else
            ToastHelper(context).toastError("Update Error")
    }

    override fun onDeleteResult(result: Boolean, msg: String, position: Int) {
        if (result) {
            adapter.removeItem(position)
            ToastHelper(context).toastSuccess("Item has been remove")
        } else
            ToastHelper(context).toastError("Delete Error")
    }
}