package com.trangiabao.sixjars.utils.helper

import com.trangiabao.sixjars.utils.AppConstants
import java.math.BigDecimal
import java.text.DecimalFormat

object NumberHelper {

    fun printBigDouble(value: Double): String {
        val df = DecimalFormat(AppConstants.DECIMAL_FORMAT)
        return df.format(BigDecimal(value))
    }
}