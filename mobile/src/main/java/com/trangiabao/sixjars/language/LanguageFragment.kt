package com.trangiabao.sixjars.language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.base.BaseFragment
import com.trangiabao.sixjars.base.LocaleHelper
import com.trangiabao.sixjars.base.activity.HomeActivity
import kotlinx.android.synthetic.main.fragment_language.view.*

class LanguageFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_language, container, false)
        addEvent(view)
        return view
    }

    private fun addEvent(view: View) {
        view.cardViewEng.setOnClickListener {
            if (LocaleHelper.getLanguage(context) != "en") {
                LocaleHelper.setLocale(context, "en")
                refresh()
            }
        }

        view.cardViewVie.setOnClickListener {
            if (LocaleHelper.getLanguage(context) != "vi") {
                LocaleHelper.setLocale(context, "vi")
                refresh()
            }
        }
    }

    private fun refresh() {
        startActivity(HomeActivity::class.java)
        finish()
    }
}
