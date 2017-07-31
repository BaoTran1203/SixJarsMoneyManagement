package com.trangiabao.sixjars.utils.helper

import android.content.Context
import android.widget.Toast
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.utils.toast.CustomToast
import com.trangiabao.sixjars.utils.toast.ToastEnum

object ToastHelper {

    fun toastSuccess(context: Context, msg: Int) {
        CustomToast.Builder(context)
                .withType(ToastEnum.SUCCESS)
                .withMessage(msg)
                .withIcon(R.drawable.ic_done)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastSuccess(context: Context, msg: String) {
        CustomToast.Builder(context)
                .withType(ToastEnum.SUCCESS)
                .withMessage(msg)
                .withIcon(R.drawable.ic_done)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastError(context: Context, msg: Int) {
        CustomToast.Builder(context)
                .withType(ToastEnum.ERROR)
                .withMessage(msg)
                .withIcon(R.drawable.ic_error)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastError(context: Context, msg: String) {
        CustomToast.Builder(context)
                .withType(ToastEnum.ERROR)
                .withMessage(msg)
                .withIcon(R.drawable.ic_error)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastWarning(context: Context, msg: Int) {
        CustomToast.Builder(context)
                .withType(ToastEnum.WARNING)
                .withMessage(msg)
                .withIcon(R.drawable.ic_warning)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }

    fun toastWarning(context: Context, msg: String) {
        CustomToast.Builder(context)
                .withType(ToastEnum.WARNING)
                .withMessage(msg)
                .withIcon(R.drawable.ic_warning)
                .withDuration(Toast.LENGTH_SHORT)
                .show()
    }


}