package com.trangiabao.sixjars.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.activity.HomeActivity
import com.trangiabao.sixjars.system.LocaleHelper
import kotlinx.android.synthetic.main.fragment_language.view.*

class LanguageFragment : Fragment() {

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
        startActivity(Intent(context, HomeActivity::class.java))
    }
}
