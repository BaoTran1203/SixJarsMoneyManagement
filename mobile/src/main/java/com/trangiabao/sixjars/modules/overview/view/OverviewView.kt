package com.trangiabao.sixjars.modules.overview.view

import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.utils.base.BaseView

interface OverviewView : BaseView {
    fun onListLoaded(result: Boolean, msg: String, list: List<Jar>)
}