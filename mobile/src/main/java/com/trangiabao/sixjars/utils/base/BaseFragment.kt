package com.trangiabao.sixjars.utils.base

import android.content.Intent
import android.support.v4.app.Fragment
import android.util.Log

abstract class BaseFragment : Fragment() {

    private val TAG: String = "TAGTAG"

    protected fun startActivity(newClass: Class<*>) {
        startActivity(Intent(context, newClass))
    }

    protected fun finish() {
        activity.finish()
    }

    protected fun log(msg: String) {
        Log.d(TAG, msg)
    }

    protected fun log(msg: Int) {
        Log.d(TAG, getString(msg))
    }
}