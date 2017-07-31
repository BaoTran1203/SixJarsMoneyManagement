package com.trangiabao.sixjars.modules.statistical_page.presenter

import com.trangiabao.sixjars.modules.statistical_page.view.StatisticalView

class StatisticalPresenter(private var view: StatisticalView) : StatisticalPresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

}