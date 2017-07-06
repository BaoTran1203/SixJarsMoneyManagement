package com.trangiabao.sixjars.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.adapter.ExpenditureTypeAdapter
import com.trangiabao.sixjars.adapter.ExpenditureTypeAdapter.ItemClickListener
import com.trangiabao.sixjars.model.ExpenditureType
import com.trangiabao.sixjars.model.RevenueType
import com.trangiabao.sixjars.presenter.CatalogPresenter
import com.trangiabao.sixjars.view.CatalogView
import kotlinx.android.synthetic.main.fragment_type.view.*

class ExpenditureTypeFragment : Fragment(), CatalogView {

    private var adapter: ExpenditureTypeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_type, container, false)
        setupUI(view)
        val presenter = CatalogPresenter(context, this)
        presenter.getExpenditureType()
        return view
    }

    private fun setupUI(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ExpenditureTypeAdapter(object : ItemClickListener {
            override fun onClickListener(type: ExpenditureType, position: Int) {
                // Edit
                openDialog(getString(R.string.edit), "Edit bla bla bla", R.drawable.ic_estimate)
                Log.d("TAGTAG", "Click at $position")
            }

            override fun onLongClickListener(type: ExpenditureType, position: Int): Boolean {
                // Delete
                Log.d("TAGTAG", "Long Click at $position")
                return true
            }

        })
        view.recyclerView.adapter = adapter
    }

    override fun onExpenditureTypeLoaded(types: MutableList<ExpenditureType>) {
        adapter!!.updateList(types)
    }

    override fun onRevenueTypeLoaded(types: MutableList<RevenueType>) {
        TODO("not implemented")
    }

    @SuppressLint("InflateParams")
    private fun openDialog(title: String, content: String, icon: Int) {
        val alertDialog = AlertDialog.Builder(context)
        val view: View = layoutInflater.inflate(R.layout.dialog, null)
        alertDialog.setView(view)
        alertDialog.setTitle(title)
        alertDialog.setIcon(icon)
        alertDialog.setMessage(content)
        alertDialog.setPositiveButton(getString(R.string.confirm)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
        //val = view.findViewById(R.id.et_country) as EditText
    }
}
