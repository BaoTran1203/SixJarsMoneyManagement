package com.trangiabao.sixjars.utils.base

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.trangiabao.sixjars.utils.AppConstants
import com.trangiabao.sixjars.utils.helper.LocaleHelper

@Suppress("unused")
abstract class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    protected fun activity(newClass: Class<*>) {
        val intent = Intent(applicationContext, newClass)
        startActivity(intent)
    }

    protected fun fragment(containerViewId: Int, newClass: Class<*>) {
        val fragment: Fragment = newClass.newInstance() as Fragment
        val fragmentManager = supportFragmentManager
        val trans = fragmentManager.beginTransaction()
        trans.replace(containerViewId, fragment)
        trans.commit()
    }

    protected fun log(message: String) {
        Log.d(AppConstants.LOG_TAG, message)
    }

    protected fun freeMemory() {
        System.runFinalization()
        Runtime.getRuntime().gc()
        System.gc()
    }
}