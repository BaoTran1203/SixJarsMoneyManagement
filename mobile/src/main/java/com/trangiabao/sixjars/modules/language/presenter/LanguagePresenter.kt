package com.trangiabao.sixjars.modules.language.presenter

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.modules.language.view.LanguageView

class LanguagePresenter(private var view: LanguageView) : LanguagePresenterImpl {

    override fun createView() {
        view.onInitControls()
        view.onInitEvents()
    }

    override fun changeLanguage(curLang: String, newlang: String) {
        if (curLang == newlang) {
            view.onLanguageHadChangged(R.string.app_name)
        } else {
            view.onLanguageIsChanged(R.string.app_name, newlang)
        }
    }
}