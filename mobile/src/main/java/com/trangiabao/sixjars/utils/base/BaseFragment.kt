package com.trangiabao.sixjars.utils.base

import android.content.Intent
import android.support.v4.app.Fragment
import android.util.Log
import com.trangiabao.sixjars.utils.AppConstants

abstract class BaseFragment : Fragment() {

    protected fun startActivity(newClass: Class<*>) {
        startActivity(Intent(context, newClass))
    }

    protected fun finish() {
        activity.finish()
    }

    protected fun log(msg: String) {
        Log.d(AppConstants.LOG_TAG, msg)
    }

    protected fun log(msg: Int) {
        Log.d(AppConstants.LOG_TAG, getString(msg))
    }
}