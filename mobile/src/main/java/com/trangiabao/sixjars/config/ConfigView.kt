package com.trangiabao.sixjars.config

import com.trangiabao.sixjars.model.Jar

interface ConfigView {
    fun onListLoaded(list: MutableList<Jar>)
    fun onUpdateResult(result: Boolean, msg: String)
}