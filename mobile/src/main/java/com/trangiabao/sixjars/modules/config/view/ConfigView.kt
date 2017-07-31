package com.trangiabao.sixjars.modules.config.view

import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.utils.base.BaseView

interface ConfigView : BaseView {
    fun onListLoaded(result: Boolean, msg: String, list: List<Jar>)
    fun onUpdateResult(result: Boolean, msg: String, list: List<Jar>)
}