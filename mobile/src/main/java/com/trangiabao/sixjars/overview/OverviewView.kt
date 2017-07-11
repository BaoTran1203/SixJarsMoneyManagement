package com.trangiabao.sixjars.overview

import com.trangiabao.sixjars.base.model.Jar

interface OverviewView {
    fun onListLoaded(result: Boolean, msg: String, list: List<Jar>)
}