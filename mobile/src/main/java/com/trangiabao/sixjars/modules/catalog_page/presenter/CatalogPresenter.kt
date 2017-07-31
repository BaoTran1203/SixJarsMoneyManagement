package com.trangiabao.sixjars.modules.catalog_page.presenter

import com.trangiabao.sixjars.modules.catalog_page.view.CatalogView

class CatalogPresenter(private var view: CatalogView) : CatalogPresenterImpl {

    override fun createView() {
        view.onInitControls()
    }

}