package com.trangiabao.sixjars.modules.language.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.modules.home.view.HomeActivity
import com.trangiabao.sixjars.modules.language.presenter.LanguagePresenter
import com.trangiabao.sixjars.utils.base.BaseFragment
import com.trangiabao.sixjars.utils.component.toast.ToastHelper
import com.trangiabao.sixjars.utils.helper.LocaleHelper
import kotlinx.android.synthetic.main.fragment_language.view.*

class LanguageFragment : BaseFragment(), LanguageView {

    private var mView: View? = null
    private var presenter: LanguagePresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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
                presenter!!.changeLanguage(LocaleHelper.getLanguage(context), "en")
            }

            cardViewVie.setOnClickListener {
                presenter!!.changeLanguage(LocaleHelper.getLanguage(context), "vi")
            }
        }
    }

    override fun onLanguageChanged(result: Boolean, msg: String, lang: String) {
        if (result) {
            LocaleHelper.setLocale(context, lang)
            startActivity(HomeActivity::class.java)
            finish()
        }
        ToastHelper(context).toastSuccess(msg)
    }
}
