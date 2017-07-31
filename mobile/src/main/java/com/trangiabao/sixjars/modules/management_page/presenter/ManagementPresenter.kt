package com.trangiabao.sixjars.modules.management_page.presenter

import com.trangiabao.sixjars.modules.management_page.view.ManagementView

class ManagementPresenter(private var view: ManagementView) : ManagementPresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

}