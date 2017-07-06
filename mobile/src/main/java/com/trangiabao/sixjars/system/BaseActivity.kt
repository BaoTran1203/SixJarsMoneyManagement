package com.trangiabao.sixjars.system

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

@Suppress("unused")
abstract class BaseActivity : AppCompatActivity() {

    private val TAG: String = "BaseActivityTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    protected fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    protected fun startActivity(newClass: Class<*>) {
        val intent = Intent(applicationContext, newClass)
        startActivity(intent)
    }

    protected fun showLog(message: String) {
        Log.d(TAG, message)
    }

    protected fun startNewFragment(containerViewId: Int, newClass: Class<*>) {
        val fragment: Fragment = newClass.newInstance() as Fragment
        val fragmentManager = supportFragmentManager
        val trans = fragmentManager.beginTransaction()
        trans.replace(containerViewId, fragment)
        trans.commit()
    }

    protected fun freeMemory() {
        System.runFinalization()
        Runtime.getRuntime().gc()
        System.gc()
    }

    override fun onStop() {
        super.onStop()
        // backup here
    }
}