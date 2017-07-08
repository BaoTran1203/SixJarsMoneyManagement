package com.trangiabao.sixjars.base

import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast

abstract class BaseFragment : Fragment() {

    private val TAG: String = "BaseActivityTag"

    protected fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun toast(msg: Int) {
        Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
    }

    protected fun log(msg: String) {
        Log.d(TAG, msg)
    }

}