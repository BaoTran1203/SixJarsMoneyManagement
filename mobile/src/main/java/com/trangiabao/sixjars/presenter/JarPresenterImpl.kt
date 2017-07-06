package com.trangiabao.sixjars.presenter

import com.trangiabao.sixjars.model.Jar

interface JarPresenterImpl {

    fun getAll()

    fun update(list: MutableList<Jar>)

}