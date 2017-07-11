package com.trangiabao.sixjars.base.activity

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.catalog.CatalogFragment
import com.trangiabao.sixjars.config.ConfigFragment
import com.trangiabao.sixjars.estimate.EstimateFragment
import com.trangiabao.sixjars.language.LanguageFragment
import com.trangiabao.sixjars.management.MoneyManagementFragment
import com.trangiabao.sixjars.overview.OverviewFragment
import com.trangiabao.sixjars.statistical.StatisticalFragment
import com.trangiabao.sixjars.wallet.WalletFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.custom_app_bar.*

class HomeActivity : BaseActivity() {

    private var curFragment: Class<*> = OverviewFragment::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        initNavigationDrawer()
    }

    override fun onStart() {
        super.onStart()
        navigationView.menu.getItem(0).isChecked = true
        fragment(R.id.home_content, curFragment)
        title = navigationView.menu.getItem(0).title
    }

    fun initNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            when (id) {
                R.id.itemOverview -> {
                    curFragment = OverviewFragment::class.java
                }
                R.id.itemWallet -> {
                    curFragment = WalletFragment::class.java
                }
                R.id.itemManagement -> {
                    curFragment = MoneyManagementFragment::class.java
                }
                R.id.itemEstimate -> {
                    curFragment = EstimateFragment::class.java
                }
                R.id.itemStatistical -> {
                    curFragment = StatisticalFragment::class.java
                }
                R.id.itemJarConfig -> {
                    curFragment = ConfigFragment::class.java
                }
                R.id.itemCatalog -> {
                    curFragment = CatalogFragment::class.java
                }
                R.id.itemLanguage -> {
                    curFragment = LanguageFragment::class.java
                }
            }
            fragment(R.id.home_content, curFragment)
            freeMemory()
            menuItem.isChecked = true
            title = menuItem.title
            drawerLayout.closeDrawers()
            true
        }
        //val drawerHeader = navigationView.getHeaderView(0)
        val drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerClosed(v: View?) {
                super.onDrawerClosed(v)
            }

            override fun onDrawerOpened(v: View?) {
                super.onDrawerOpened(v)
            }
        }
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }
}