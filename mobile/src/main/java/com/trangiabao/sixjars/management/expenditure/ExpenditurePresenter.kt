package com.trangiabao.sixjars.management.expenditure

import com.trangiabao.sixjars.base.database.ExpenditureDB
import org.joda.time.DateTime
import java.util.*

class ExpenditurePresenter(private var view: ExpenditureView) : ExpenditurePresenterImpl {

    override fun filter(from: DateTime, to: DateTime) {
        val list = ExpenditureDB.find(getStartDate(from), getEndDate(to))
        view.onGetListResult(list.isNotEmpty(), "", list)
    }

    override fun delete(id: String, position: Int) {
        val result = ExpenditureDB.delete(id)
        val msg = if (result) "Item has been removed" else "Failed"
        view.onDeleteResult(result, msg, position)
    }

    private fun getStartDate(date: DateTime): Date {
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

    private fun getEndDate(date: DateTime): Date {
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
}