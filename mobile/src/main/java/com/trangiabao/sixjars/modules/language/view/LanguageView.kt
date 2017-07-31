package com.trangiabao.sixjars.modules.language.view

import com.trangiabao.sixjars.utils.base.BaseView

interface LanguageView : BaseView {
    fun onLanguageIsChanged(msg: Int, lang: String)
    fun onLanguageHadChangged(msg: Int)
}