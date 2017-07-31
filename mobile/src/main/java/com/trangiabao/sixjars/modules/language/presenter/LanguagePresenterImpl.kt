package com.trangiabao.sixjars.modules.language.presenter

import com.trangiabao.sixjars.utils.base.BasePresenter

interface LanguagePresenterImpl : BasePresenter {
    fun changeLanguage(curLang: String, newlang: String)
}