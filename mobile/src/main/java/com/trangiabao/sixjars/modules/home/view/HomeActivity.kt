package com.trangiabao.sixjars.modules.home.view

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.modules.catalog_page.view.CatalogFragment
import com.trangiabao.sixjars.modules.config.view.ConfigFragment
import com.trangiabao.sixjars.modules.estimate.view.EstimateFragment
import com.trangiabao.sixjars.modules.home.presenter.HomePresenter
import com.trangiabao.sixjars.modules.language.view.LanguageFragment
import com.trangiabao.sixjars.modules.management_page.view.ManagementFragment
import com.trangiabao.sixjars.modules.overview.view.OverviewFragment
import com.trangiabao.sixjars.modules.statistical_page.view.StatisticalFragment
import com.trangiabao.sixjars.modules.wallet.view.WalletFragment
import com.trangiabao.sixjars.utils.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_app_bar.*

class HomeActivity : BaseActivity(), HomeView {

    private var curFragment: Class<*> = OverviewFragment::class.java
    private var presenter: HomePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(this)
        presenter!!.createView()
    }

    override fun onResume() {
        super.onResume()
        navigationView.menu.getItem(0).isChecked = true
        fragment(R.id.home_content, curFragment)
        title = navigationView.menu.getItem(0).title
    }

    override fun onInitControls() {
        setSupportActionBar(toolbar)
    }

    override fun onInitEvents() {
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
                    curFragment = ManagementFragment::class.java
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
