package com.trangiabao.sixjars.management.revenue

import com.trangiabao.sixjars.base.database.RevenueDB
import com.trangiabao.sixjars.base.model.Revenue
import java.util.*

class RevenuePresenter(private var view: RevenueView) : RevenuePresenterImpl {

    override fun filter(from: Date, to: Date) {
        view.onGetListResult(RevenueDB.find(getStartDate(from), getEndDate(to)))
    }

    override fun add(revenue: Revenue) {
        view.onAddResult(RevenueDB.add(revenue))
    }

    override fun update(revenue: Revenue) {
        view.onUpdateResult(RevenueDB.update(revenue))
    }

    override fun delete(id: String) {
        view.onDeleteResult(RevenueDB.delete(id))
    }

    private fun getStartDate(date: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }

    private fun getEndDate(date: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)
        return cal.time
    }
}