package com.trangiabao.sixjars.modules.estimate.view

import com.trangiabao.sixjars.data.model.Jar
import com.trangiabao.sixjars.utils.base.BaseView

interface EstimateView : BaseView {
    fun onListLoaded(result: Boolean, msg: String, list: List<Jar>)
}