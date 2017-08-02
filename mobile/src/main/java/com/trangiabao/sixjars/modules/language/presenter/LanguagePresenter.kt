package com.trangiabao.sixjars.modules.language.presenter

import com.trangiabao.sixjars.modules.language.view.LanguageView

class LanguagePresenter(private var view: LanguageView) : LanguagePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun changeLanguage(curLang: String, newlang: String) {
        if (curLang != newlang) {
            view.onLanguageChanged(newlang)
        }
    }
}