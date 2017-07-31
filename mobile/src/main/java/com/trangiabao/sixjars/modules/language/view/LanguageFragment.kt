package com.trangiabao.sixjars.modules.language.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.modules.home.view.HomeActivity
import com.trangiabao.sixjars.modules.language.presenter.LanguagePresenter
import com.trangiabao.sixjars.utils.AppConstants
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.helper.LocaleHelper
import com.trangiabao.sixjars.utils.helper.ToastHelper
import kotlinx.android.synthetic.main.fragment_language.view.*

class LanguageFragment : BaseFragment(), LanguageView {

    private var mView: View? = null
    private var presenter: LanguagePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_language, container, false)
        presenter = LanguagePresenter(this)
        presenter!!.createView()
        return mView
    }

    override fun onInitControls() {
    }

    override fun onInitEvents() {
        mView!!.run {
            cardViewEng.setOnClickListener {
                presenter!!.changeLanguage(LocaleHelper.getLanguage(context), AppConstants.LANG_CODE_EN)
            }

            cardViewVie.setOnClickListener {
                presenter!!.changeLanguage(LocaleHelper.getLanguage(context), AppConstants.LANG_CODE_VI)
            }
        }
    }

    override fun onLanguageIsChanged(msg: Int, lang: String) {
        LocaleHelper.setLocale(context, lang)
        startActivity(HomeActivity::class.java)
        finish()
        ToastHelper.toastSuccess(context, msg)
    }

    override fun onLanguageHadChangged(msg: Int) {
        ToastHelper.toastWarning(context, msg)
    }
}
