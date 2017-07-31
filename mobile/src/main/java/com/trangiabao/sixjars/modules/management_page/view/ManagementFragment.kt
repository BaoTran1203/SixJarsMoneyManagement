package com.trangiabao.sixjars.modules.management_page.view

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
import com.trangiabao.sixjars.modules.m_expenditure.view.ExpenditureFragment
import com.trangiabao.sixjars.modules.m_revenue.view.RevenueFragment
import com.trangiabao.sixjars.modules.management_page.presenter.ManagementPresenter
import com.trangiabao.sixjars.utils.component.ViewPagerAdapter
import com.trangiabao.sixjars.utils.component.ViewPagerTransformer

class ManagementFragment : Fragment(), ManagementView {

    private var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_management, container, false)
        ManagementPresenter(this).createView()
        return mView
    }

    @Suppress("DEPRECATION")
    override fun onInitControls() {
        val viewPager: ViewPager = mView!!.findViewById(R.id.viewPager)
        val tabLayout: TabLayout = mView!!.findViewById(R.id.tabLayout)

        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(RevenueFragment(), getString(R.string.revenue))
        adapter.addFragment(ExpenditureFragment(), getString(R.string.expenditure))
        viewPager.adapter = adapter
        viewPager.setPageTransformer(true, ViewPagerTransformer())

        // setup Title and Icon
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.customView = getTextView(R.string.revenue, R.drawable.ic_revenue)
        tabLayout.getTabAt(1)!!.customView = getTextView(R.string.expenditure, R.drawable.ic_expenditure)

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

    override fun onInitEvents() {
    }

    @SuppressLint("InflateParams")
    private fun getTextView(title: Int, icon: Int): TextView {
        val textView: TextView = LayoutInflater.from(context).inflate(R.layout.custom_tab, null) as TextView
        textView.text = getString(title)
        textView.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        return textView
    }
}
