package com.trangiabao.sixjars.overview

import com.trangiabao.sixjars.model.Jar

interface OverviewView {
    fun onListLoaded(list: MutableList<Jar>)
}