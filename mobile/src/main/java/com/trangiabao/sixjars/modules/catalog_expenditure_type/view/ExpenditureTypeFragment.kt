package com.trangiabao.sixjars.modules.catalog_expenditure_type.view

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.ExpenditureType
import com.trangiabao.sixjars.modules.catalog_expenditure_type.adapter.ExpenditureTypeAdapter
import com.trangiabao.sixjars.modules.catalog_expenditure_type.presenter.ExpenditureTypePresenter
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.component.ScrollAwareFABBehavior
import com.trangiabao.sixjars.utils.dialog.CustomDialogConfirm
import com.trangiabao.sixjars.utils.dialog.catalog.CatalogDialog
import com.trangiabao.sixjars.utils.dialog.catalog.CatalogEnum
import com.trangiabao.sixjars.utils.helper.ToastHelper
import kotlinx.android.synthetic.main.fragment_type.view.*
import java.util.*

class ExpenditureTypeFragment : BaseFragment(), ExpenditureTypeView {

    private var mView: View? = null
    private var adapter = ExpenditureTypeAdapter()
    private var presenter: ExpenditureTypePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_type, container, false)
        presenter = ExpenditureTypePresenter(this)
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
        adapter.setOnItemClickListener(object : ExpenditureTypeAdapter.ItemClickListener {
            override fun onClickListener(obj: ExpenditureType, position: Int) {
                CatalogDialog.Builder(context)
                        .withType(obj.type!!)
                        .withDescription(obj.description!!)
                        .withEnum(CatalogEnum.EDIT)
                        .setOnConfirmClick(object : CatalogDialog.OnConfirmListener {
                            override fun onResult(dialog: CatalogDialog, result: Boolean, type: String, description: String) {
                                if (result) {
                                    val expenditureType = ExpenditureType()
                                    expenditureType.id = obj.id
                                    expenditureType.type = type
                                    expenditureType.description = description
                                    presenter!!.update(expenditureType)
                                }
                                dialog.dismiss()
                            }
                        })
                        .show()
            }

            override fun onLongClickListener(obj: ExpenditureType, position: Int): Boolean {
                CustomDialogConfirm.Builder(context)
                        .withTitle(R.string.confirm)
                        .withContent(R.string.deletion)
                        .withIcon(R.drawable.ic_delete)
                        .setOnConfirmClick(object : CustomDialogConfirm.OnConfirmListener {
                            override fun onResult(dialog: CustomDialogConfirm, result: Boolean) {
                                if (result)
                                    presenter!!.delete(obj.id!!, position)
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
                                val expenditureType = ExpenditureType()
                                expenditureType.id = UUID.randomUUID().toString()
                                expenditureType.type = type
                                expenditureType.description = description
                                presenter!!.update(expenditureType)
                            }
                            dialog.dismiss()
                        }
                    }).show()
        }
    }

    override fun onGetListSuccessed(list: List<ExpenditureType>) {
        val temp = list.toMutableList()
        temp.removeAt(0)
        adapter.updateList(temp)
    }

    override fun onGetListFailed(msg: Int) {
        ToastHelper.toastError(context, msg)
    }

    override fun onUpdateSuccessed(msg: Int, expenditureType: ExpenditureType) {
        adapter.updateItem(expenditureType)
        ToastHelper.toastSuccess(context, msg)
    }

    override fun onUpdateFailed(msg: Int) {
        ToastHelper.toastError(context, msg)
    }

    override fun onDeleteSuccessed(msg: Int, position: Int) {
        adapter.removeItem(position)
        ToastHelper.toastSuccess(context, msg)
    }

    override fun onDeleteFailed(msg: Int) {
        ToastHelper.toastError(context, R.string.app_name)
    }
}