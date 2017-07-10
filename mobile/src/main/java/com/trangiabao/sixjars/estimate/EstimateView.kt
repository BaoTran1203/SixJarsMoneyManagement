package com.trangiabao.sixjars.estimate

import com.trangiabao.sixjars.base.model.Jar

interface EstimateView {
    fun onListLoaded(list: List<Jar>)
}