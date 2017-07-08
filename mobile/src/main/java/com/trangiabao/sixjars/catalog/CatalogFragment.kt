package com.trangiabao.sixjars.catalog

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.ui.ViewPagerAdapter
import com.trangiabao.sixjars.base.ui.ViewPagerTransformer
import com.trangiabao.sixjars.catalog.expenditure.ExpenditureTypeFragment
import com.trangiabao.sixjars.catalog.revenue.RevenueTypeFragment

class CatalogFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_catalog, container, false)
        setupUI(view)
        return view
    }

    @Suppress("DEPRECATION")
    private fun setupUI(view: View) {
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        // setup ViewPager
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(RevenueTypeFragment(), getString(R.string.revenue_type))
        adapter.addFragment(ExpenditureTypeFragment(), getString(R.string.expenditure_type))
        viewPager.adapter = adapter
        viewPager.setPageTransformer(true, ViewPagerTransformer())
        // setup Title and Icon
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.customView = getTextView(R.string.revenue_type, R.drawable.ic_revenue)
        tabLayout.getTabAt(1)!!.customView = getTextView(R.string.expenditure_type, R.drawable.ic_expenditure)
        // setup Divider for TabLayout
        val root = tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.colorDivider))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
    }

    @SuppressLint("InflateParams")
    private fun getTextView(title: Int, icon: Int): TextView {
        val textView: TextView = LayoutInflater.from(context).inflate(R.layout.custom_tab, null) as TextView
        textView.text = getString(title)
        textView.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        return textView
    }
}
