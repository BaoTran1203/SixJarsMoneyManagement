package com.trangiabao.sixjars.ui

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

class NumericEditText(context: Context, attrs: AttributeSet) : TextInputEditText(context, attrs) {

    private var mDefaultText: String? = null
    private var mPreviousText = ""
    private val mNumberFilterRegex = "[^\\d\\$DECIMAL_SEPARATOR]"
    private val mNumericListeners = ArrayList<NumericValueWatcher>()

    private val mTextWatcher = object : TextWatcher {
        private var validateLock = false

        override fun afterTextChanged(s: Editable) {
            if (validateLock)
                return
            if (countMatches(s.toString(), DECIMAL_SEPARATOR.toString()) > 1) {
                validateLock = true
                setText(mPreviousText)
                setSelection(mPreviousText.length)
                validateLock = false
                return
            }
            if (s.isEmpty()) {
                handleNumericValueCleared()
                return
            }
            setTextInternal(format(s.toString()))
            setSelection(text.length)
            handleNumericValueChanged()
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    }

    init {
        addTextChangedListener(mTextWatcher)
        setOnClickListener { setSelection(text.length) }
    }

    private fun handleNumericValueCleared() {
        mPreviousText = ""
        for (listener in mNumericListeners) {
            listener.onCleared()
        }
    }

    private fun handleNumericValueChanged() {
        mPreviousText = text.toString()
        for (listener in mNumericListeners) {
            listener.onChanged(numericValue)
        }
    }

    fun addNumericValueChangedListener(watcher: NumericValueWatcher) {
        mNumericListeners.add(watcher)
    }

    fun removeAllNumericValueChangedListeners() {
        while (!mNumericListeners.isEmpty()) {
            mNumericListeners.removeAt(0)
        }
    }

    fun clear() {
        setTextInternal(mDefaultText!!)
        if (mDefaultText != null) {
            handleNumericValueChanged()
        }
    }

    val numericValue: Double
        get() {
            val original = text.toString().replace(mNumberFilterRegex.toRegex(), "")
            try {
                return NumberFormat.getInstance().parse(original).toDouble()
            } catch (e: ParseException) {
                return java.lang.Double.NaN
            }

        }

    private fun format(original: String): String {
        val parts = original.split(("\\" + DECIMAL_SEPARATOR).toRegex()).toTypedArray()
        var number = parts[0].replace(mNumberFilterRegex.toRegex(), "")
                .replaceFirst(LEADING_ZERO_FILTER_REGEX.toRegex(), "")
        number = reverse(reverse(number).replace("(.{3})".toRegex(), "$1$GROUPING_SEPARATOR"))
        number = removeStart(number, GROUPING_SEPARATOR.toString())
        if (parts.size > 1) {
            number += DECIMAL_SEPARATOR + parts[1]
        }
        return number
    }

    private fun setTextInternal(text: String) {
        removeTextChangedListener(mTextWatcher)
        setText(text)
        addTextChangedListener(mTextWatcher)
    }

    @Suppress("DEPRECATION")
    private fun reverse(original: String?): String {
        if (original == null || original.length <= 1) {
            return original!!
        }
        return TextUtils.getReverse(original, 0, original.length).toString()
    }

    private fun removeStart(str: String, remove: String): String {
        if (TextUtils.isEmpty(str)) {
            return str
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length)
        }
        return str
    }

    private fun countMatches(str: String, sub: String): Int {
        if (TextUtils.isEmpty(str)) {
            return 0
        }
        val lastIndex = str.lastIndexOf(sub)
        if (lastIndex < 0) {
            return 0
        } else {
            return 1 + countMatches(str.substring(0, lastIndex), sub)
        }
    }

    interface NumericValueWatcher {
        fun onChanged(newValue: Double)

        fun onCleared()
    }

    companion object {
        private val GROUPING_SEPARATOR = DecimalFormatSymbols.getInstance().groupingSeparator
        private val DECIMAL_SEPARATOR = DecimalFormatSymbols.getInstance().decimalSeparator
        private val LEADING_ZERO_FILTER_REGEX = "^0+(?!$)"
    }
}
