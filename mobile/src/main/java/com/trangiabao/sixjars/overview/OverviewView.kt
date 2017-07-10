package com.trangiabao.sixjars.overview

import com.trangiabao.sixjars.base.model.Jar

interface OverviewView {
    fun onListLoaded(list: List<Jar>)
}