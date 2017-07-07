package com.trangiabao.sixjars.system

import android.app.Application
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast

abstract class BaseFragment : Fragment() {

    private val TAG: String = "BaseActivityTag"
    private val PREF_FILE_NAME: String = "Save"

    protected fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun toast(msg: Int) {
        Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
    }

    protected fun log(msg: String) {
        Log.d(TAG, msg)
    }

    protected fun saveValue(key: String, value: String) {
        val pre: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Application.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pre.edit()
        editor.putString(key, value)
        editor.apply()
    }
}