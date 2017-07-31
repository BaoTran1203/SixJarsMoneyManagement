package com.trangiabao.sixjars.modules.language.presenter

import com.trangiabao.sixjars.modules.language.view.LanguageView

class LanguagePresenter(private var view: LanguageView) : LanguagePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun changeLanguage(curLang: String, newlang: String) {
        val result: Boolean
        val msg: String
        if (curLang == newlang) {
            result = false
            msg = "Da chon ngon ngu hien tai"
        } else {
            result = true
            msg = "Changed"
        }
        view.onLanguageChanged(result, msg, newlang)
    }
}