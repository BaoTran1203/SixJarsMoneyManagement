package com.trangiabao.sixjars.management.revenue

import com.trangiabao.sixjars.model.Revenue
import com.trangiabao.sixjars.model.RevenueType
import java.util.*

interface RevenuePresenterImpl {

    fun filter()
    fun filter(type: RevenueType)
    fun filter(dateStart: Date?, dateEnd: Date)
    fun filter(dateStart: Date?, dateEnd: Date, type: RevenueType)
    fun add(revenue: Revenue)
    fun update(revenue: Revenue)
    fun delete(id: String)

}