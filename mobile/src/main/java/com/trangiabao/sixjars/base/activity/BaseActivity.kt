package com.trangiabao.sixjars.base.activity

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.ui.toast.CustomToast
import com.trangiabao.sixjars.ui.toast.ToastEnum

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

    protected fun toastSuccess(msg: Int) {
        CustomToast.Builder(applicationContext, this)
                .withType(ToastEnum.SUCCESS)
                .withMessage(msg)
                .withIcon(R.drawable.ic_done)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastSuccess(msg: String) {
        CustomToast.Builder(applicationContext, this)
                .withType(ToastEnum.SUCCESS)
                .withMessage(msg)
                .withIcon(R.drawable.ic_done)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastError(msg: Int) {
        CustomToast.Builder(applicationContext, this)
                .withType(ToastEnum.ERROR)
                .withMessage(msg)
                .withIcon(R.drawable.ic_error)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastError(msg: String) {
        CustomToast.Builder(applicationContext, this)
                .withType(ToastEnum.ERROR)
                .withMessage(msg)
                .withIcon(R.drawable.ic_error)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastWarning(msg: Int) {
        CustomToast.Builder(applicationContext, this)
                .withType(ToastEnum.WARNING)
                .withMessage(msg)
                .withIcon(R.drawable.ic_warning)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastWarning(msg: String) {
        CustomToast.Builder(applicationContext, this)
                .withType(ToastEnum.WARNING)
                .withMessage(msg)
                .withIcon(R.drawable.ic_warning)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
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