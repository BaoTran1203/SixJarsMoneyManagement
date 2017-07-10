package com.trangiabao.sixjars.base.activity

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.trangiabao.sixjars.base.LocaleHelper

@Suppress("unused")
abstract class BaseActivity : AppCompatActivity() {

    private val TAG: String = "BaseActivityTag"

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

    protected fun toast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    protected fun log(message: String) {
        Log.d(TAG, message)
    }

    protected fun freeMemory() {
        System.runFinalization()
        Runtime.getRuntime().gc()
        System.gc()
    }
}