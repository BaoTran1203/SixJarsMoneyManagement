package com.trangiabao.sixjars.base

import android.content.Intent
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.ui.toast.CustomToast
import com.trangiabao.sixjars.ui.toast.ToastEnum

abstract class BaseFragment : Fragment() {

    private val TAG: String = "TAGTAG"

    protected fun startActivity(newClass: Class<*>) {
        startActivity(Intent(context, newClass))
    }

    protected fun finish() {
        activity.finish()
    }

    protected fun toastSuccess(msg: Int) {
        CustomToast.Builder(context, activity)
                .withType(ToastEnum.SUCCESS)
                .withMessage(msg)
                .withIcon(R.drawable.ic_done)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastSuccess(msg: String) {
        CustomToast.Builder(context, activity)
                .withType(ToastEnum.SUCCESS)
                .withMessage(msg)
                .withIcon(R.drawable.ic_done)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastError(msg: Int) {
        CustomToast.Builder(context, activity)
                .withType(ToastEnum.ERROR)
                .withMessage(msg)
                .withIcon(R.drawable.ic_error)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastError(msg: String) {
        CustomToast.Builder(context, activity)
                .withType(ToastEnum.ERROR)
                .withMessage(msg)
                .withIcon(R.drawable.ic_error)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastWarning(msg: Int) {
        CustomToast.Builder(context, activity)
                .withType(ToastEnum.WARNING)
                .withMessage(msg)
                .withIcon(R.drawable.ic_warning)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun toastWarning(msg: String) {
        CustomToast.Builder(context, activity)
                .withType(ToastEnum.WARNING)
                .withMessage(msg)
                .withIcon(R.drawable.ic_warning)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    protected fun log(msg: String) {
        Log.d(TAG, msg)
    }

    protected fun log(msg: Int) {
        Log.d(TAG, getString(msg))
    }
}