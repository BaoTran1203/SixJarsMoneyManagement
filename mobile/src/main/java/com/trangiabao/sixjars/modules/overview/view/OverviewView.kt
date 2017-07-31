package com.trangiabao.sixjars.modules.overview.view

import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.utils.base.BaseView

interface OverviewView : BaseView {
    fun onGetListSuccessed(list: List<Jar>)
    fun onGetListFailed(msg: Int)
}