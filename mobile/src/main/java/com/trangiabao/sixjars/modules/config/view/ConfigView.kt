package com.trangiabao.sixjars.modules.config.view

import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.utils.base.BaseView

interface ConfigView : BaseView {
    fun onGetListSuccessed(list: List<Jar>)
    fun onUpdateSuccessed(msg: Int, list: List<Jar>)

    fun onError(msg: Int)
    fun onWarning(msg: Int)
}