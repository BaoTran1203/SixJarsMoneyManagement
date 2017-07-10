package com.trangiabao.sixjars.config

import com.trangiabao.sixjars.base.model.Jar

interface ConfigPresenterImpl {
    fun getAll()
    fun update(list: List<Jar>)
}