package com.trangiabao.sixjars.utils.helper

import android.content.Context
import com.trangiabao.sixjars.utils.application.AppConstants
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.util.*

object DateTimeHelper {

    fun getStartDate(date: DateTime): Date {
        return DateTime()
                .withYear(date.year)
                .withMonthOfYear(date.monthOfYear)
                .withDayOfMonth(date.dayOfMonth)
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0)
                .toDate()
    }

    fun getEndDate(date: DateTime): Date {
        return DateTime()
                .withYear(date.year)
                .withMonthOfYear(date.monthOfYear)
                .withDayOfMonth(date.dayOfMonth)
                .withHourOfDay(23)
                .withMinuteOfHour(59)
                .withSecondOfMinute(59)
                .withMillisOfSecond(999)
                .toDate()
    }

    fun getDateFrom(): DateTime {
        return LocalDate().withDayOfWeek(DateTimeConstants.MONDAY).toDateTime(LocalTime())
    }

    fun getDateTo(): DateTime {
        return LocalDate().withDayOfWeek(DateTimeConstants.SUNDAY).toDateTime(LocalTime())
    }

    fun printDate(format: DateTimeFormatter, date: DateTime): String {
        return format.print(date)
    }

    fun getDateFormat(context: Context): DateTimeFormatter {
        return DateTimeFormat.forPattern(AppConstants.DATE_FORMAT)
                .withLocale(Locale(LocaleHelper.getLanguage(context)))
    }

    fun getTimeFormat(context: Context): DateTimeFormatter {
        return DateTimeFormat.forPattern(AppConstants.TIME_FORMAT)
                .withLocale(Locale(LocaleHelper.getLanguage(context)))
    }

    fun getMonthFormat(context: Context): DateTimeFormatter {
        return DateTimeFormat.forPattern(AppConstants.MONTH_FORMAT)
                .withLocale(Locale(LocaleHelper.getLanguage(context)))
    }

    fun getFirstDateOfMonth(date: DateTime): Date {
        return DateTime()
                .withYear(date.year)
                .withMonthOfYear(date.monthOfYear)
                .withDayOfMonth(1)
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0)
                .toDate()
    }

    fun getLastDateOfMonth(date: DateTime): Date {
        return DateTime()
                .withYear(date.year)
                .withMonthOfYear(date.monthOfYear)
                .withDayOfMonth(date.dayOfMonth().maximumValue)
                .withHourOfDay(23)
                .withMinuteOfHour(59)
                .withSecondOfMinute(59)
                .withMillisOfSecond(999)
                .toDate()
    }
}