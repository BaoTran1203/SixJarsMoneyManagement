package com.trangiabao.sixjars.management.revenue

import org.joda.time.DateTime

interface RevenuePresenterImpl {
    fun filter(from: DateTime, to: DateTime)
    fun delete(id: String, position: Int)
}