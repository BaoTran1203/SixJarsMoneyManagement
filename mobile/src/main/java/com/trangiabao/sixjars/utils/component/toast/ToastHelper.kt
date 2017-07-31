package com.trangiabao.sixjars.utils.component.toast

import android.content.Context
import android.widget.Toast
import com.trangiabao.sixjars.R

class ToastHelper(private var context: Context) {

    fun toastSuccess(msg: Int) {
        CustomToast.Builder(context)
                .withType(ToastEnum.SUCCESS)
                .withMessage(msg)
                .withIcon(R.drawable.ic_done)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastSuccess(msg: String) {
        CustomToast.Builder(context)
                .withType(ToastEnum.SUCCESS)
                .withMessage(msg)
                .withIcon(R.drawable.ic_done)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastError(msg: Int) {
        CustomToast.Builder(context)
                .withType(ToastEnum.ERROR)
                .withMessage(msg)
                .withIcon(R.drawable.ic_error)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastError(msg: String) {
        CustomToast.Builder(context)
                .withType(ToastEnum.ERROR)
                .withMessage(msg)
                .withIcon(R.drawable.ic_error)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastWarning(msg: Int) {
        CustomToast.Builder(context)
                .withType(ToastEnum.WARNING)
                .withMessage(msg)
                .withIcon(R.drawable.ic_warning)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastWarning(msg: String) {
        CustomToast.Builder(context)
                .withType(ToastEnum.WARNING)
                .withMessage(msg)
                .withIcon(R.drawable.ic_warning)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }


}