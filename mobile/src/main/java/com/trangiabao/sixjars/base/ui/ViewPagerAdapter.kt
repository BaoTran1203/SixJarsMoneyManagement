package com.trangiabao.sixjars.base.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val lstFragment: ArrayList<Fragment> = ArrayList()
    private val lstFragmentTitle: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return lstFragment[position]
    }

    override fun getCount(): Int {
        return lstFragment.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        lstFragment.add(fragment)
        lstFragmentTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return lstFragmentTitle[position]
    }
}