package com.trangiabao.sixjars.view

import com.trangiabao.sixjars.model.Jar

interface JarView {

    fun onGetListResult(list: MutableList<Jar>)

    fun onUpdateResult(result: Boolean)
}