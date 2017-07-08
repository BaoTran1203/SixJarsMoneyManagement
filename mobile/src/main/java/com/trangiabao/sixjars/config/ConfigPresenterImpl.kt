package com.trangiabao.sixjars.config

import com.trangiabao.sixjars.model.Jar

interface ConfigPresenterImpl {
    fun getAll()
    fun update(list: List<Jar>)
}