package com.trangiabao.sixjars.estimate

import com.trangiabao.sixjars.model.Jar

interface EstimateView {
    fun onListLoaded(list: List<Jar>)
}