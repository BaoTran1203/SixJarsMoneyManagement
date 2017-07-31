package com.trangiabao.sixjars.modules.estimate.view

import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.utils.base.BaseView

interface EstimateView : BaseView {
    fun onGetListSuccessed(list: List<Jar>)
    fun onError(msg: Int)
}