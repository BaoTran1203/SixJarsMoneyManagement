package com.trangiabao.sixjars.config

import com.trangiabao.sixjars.base.model.Jar

interface ConfigView {
    fun onListLoaded(result: Boolean, msg: String, list: List<Jar>)
    fun onUpdateResult(result: Boolean, msg: String, list: List<Jar>)
}