package com.trangiabao.sixjars.management.revenue

import com.trangiabao.sixjars.base.model.Revenue
import java.util.*

interface RevenuePresenterImpl {
    fun filter(from: Date, to: Date)
    fun add(revenue: Revenue)
    fun update(revenue: Revenue)
    fun delete(id: String)
}