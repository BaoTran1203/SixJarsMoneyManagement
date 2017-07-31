package com.trangiabao.sixjars.modules.config.presenter

import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.utils.base.BasePresenter

interface ConfigPresenterImpl : BasePresenter {
    fun getAll()
    fun update(list: List<Jar>)
}