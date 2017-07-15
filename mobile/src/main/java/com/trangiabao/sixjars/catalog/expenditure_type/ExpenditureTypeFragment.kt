package com.trangiabao.sixjars.catalog.expenditure_type

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.model.ExpenditureType
import com.trangiabao.sixjars.ui.ScrollAwareFABBehavior
import com.trangiabao.sixjars.ui.dialog.CustomDialogConfirm
import com.trangiabao.sixjars.ui.dialog.catalog.CatalogDialog
import com.trangiabao.sixjars.ui.dialog.catalog.CatalogEnum
import kotlinx.android.synthetic.main.fragment_type.view.*
import java.util.*

class ExpenditureTypeFragment : BaseFragment(), ExpenditureTypeView {

    private var adapter = ExpenditureTypeAdapter()
    private var presenter: ExpenditureTypePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_type, container, false)
        initControls(view)
        initEvents(view)
        presenter = ExpenditureTypePresenter(this)
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

        view.fab.setOnClickListener {
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

    override fun onGetListResult(result: Boolean, msg: String, list: List<ExpenditureType>) {
        val temp = list.toMutableList()
        temp.removeAt(0)
        adapter.updateList(temp)
    }

    override fun onUpdateResult(result: Boolean, msg: String, obj: ExpenditureType?) {
        if (obj != null) {
            adapter.updateItem(obj)
        }
    }

    override fun onDeleteResult(result: Boolean, msg: String, position: Int) {
        if (result) {
            adapter.removeItem(position)
        }
    }
}