package com.trangiabao.sixjars.modules.language.view

import com.trangiabao.sixjars.utils.base.BaseView

interface LanguageView : BaseView {
    fun onLanguageChanged(lang: String)
}