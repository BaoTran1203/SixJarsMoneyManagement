package com.trangiabao.sixjars.modules.home.presenter

import com.trangiabao.sixjars.modules.home.view.HomeView

class HomePresenter(private var view: HomeView) : HomePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

}