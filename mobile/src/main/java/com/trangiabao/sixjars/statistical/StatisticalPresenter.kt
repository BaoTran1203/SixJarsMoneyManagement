package com.trangiabao.sixjars.statistical

import com.github.mikephil.charting.data.PieEntry
import com.trangiabao.sixjars.base.database.ExpenditureDB
import com.trangiabao.sixjars.base.database.RevenueDB
import org.joda.time.DateTime
import java.util.*

class StatisticalPresenter(private var view: StatisticalView) : StatisticalPresenterImpl {

    override fun getListRevenue(date: DateTime) {
        val firstDate = getFirstDateOfMonth(date)
        val lastDate = getLastDateOfMonth(date)
        val list = RevenueDB.find(firstDate, lastDate)
        val map = list.map { it.revenueType!!.type to it.amount }.toMap()
        var entries: MutableList<PieEntry> = mutableListOf()
        for ((key, value) in map) {
            entries.add(PieEntry(value!!.toFloat(), key))
        }
        if (entries.isEmpty())
            entries.add(PieEntry(1f, "Nothing"))
        entries.sortBy { x -> x.value }
        entries = shuffle(entries)
        view.onGetListPieEntryResult(entries.isNotEmpty(), "", entries)
    }

    override fun getListExpenditure(date: DateTime) {
        val firstDate = getFirstDateOfMonth(date)
        val lastDate = getLastDateOfMonth(date)
        val list = ExpenditureDB.find(firstDate, lastDate)
        val map = list.map { it.expenditureType!!.type to it.amount }.toMap()
        var entries: MutableList<PieEntry> = mutableListOf()
        for ((key, value) in map) {
            entries.add(PieEntry(value!!.toFloat(), key))
        }
        if (entries.isEmpty())
            entries.add(PieEntry(1f, "Nothing"))
        entries.sortBy { x -> x.value }
        entries = shuffle(entries)
        view.onGetListPieEntryResult(entries.isNotEmpty(), "", entries)
    }

    private fun shuffle(items: MutableList<PieEntry>): MutableList<PieEntry> {
        val rg: Random = Random()
        for (i in 0..items.size - 1) {
            val randomPosition = rg.nextInt(items.size)
            val tmp: PieEntry = items[i]
            items[i] = items[randomPosition]
            items[randomPosition] = tmp
        }
        return items
    }

    private fun getFirstDateOfMonth(date: DateTime): Date {
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

    private fun getLastDateOfMonth(date: DateTime): Date {
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