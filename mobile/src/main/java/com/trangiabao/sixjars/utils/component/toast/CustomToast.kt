package com.trangiabao.sixjars.utils.component.toast

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.trangiabao.sixjars.R
import kotlinx.android.synthetic.main.layout_toast.view.*

@SuppressLint("InflateParams")
class CustomToast(private var context: Context,
                  type: ToastEnum?,
                  message: String?,
                  icon: Int?,
                  duration: Int?) : Toast(context) {


    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_toast, null)
        if (icon == null)
            view.imgToastIcon.visibility = View.GONE
        else {
            view.imgToastIcon.visibility = View.VISIBLE
            view.imgToastIcon.setImageResource(icon)
        }
        view.txtToastText.text = message ?: ""
        when (type) {
            ToastEnum.ERROR -> {
                view.layoutToast.background = getDrawable(R.drawable.bg_toast_error)
            }
            ToastEnum.WARNING -> {
                view.layoutToast.background = getDrawable(R.drawable.bg_toast_warning)
            }
            null, ToastEnum.SUCCESS -> {
                view.layoutToast.background = getDrawable(R.drawable.bg_toast_success)
            }
        }
        setGravity(Gravity.BOTTOM, 0, 100)
        setDuration(duration ?: Toast.LENGTH_SHORT)
        setView(view)
    }

    @Suppress("DEPRECATION")
    private fun getDrawable(resId: Int): Drawable {
        val drawable: Drawable
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = context.resources.getDrawable(resId, context.theme)
        } else {
            drawable = context.resources.getDrawable(resId)
        }
        return drawable
    }

    class Builder(private var context: Context) {
        private var type: ToastEnum? = null
        private var message: String? = null
        private var icon: Int? = null
        private var duration: Int? = null

        fun withType(type: ToastEnum): Builder {
            this.type = type
            return this
        }

        fun withMessage(msg: Int): Builder {
            this.message = context.getString(msg)
            return this
        }

        fun withMessage(msg: String): Builder {
            this.message = msg
            return this
        }

        fun withIcon(icon: Int): Builder {
            this.icon = icon
            return this
        }

        fun withDuration(duration: Int): Builder {
            this.duration = duration
            return this
        }

        fun show() = CustomToast(context, type, message, icon, duration).show()
    }
}