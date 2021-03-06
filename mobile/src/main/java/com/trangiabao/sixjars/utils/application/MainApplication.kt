package com.trangiabao.sixjars.utils.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.data.model.Database
import com.trangiabao.sixjars.utils.helper.LocaleHelper
import io.realm.Realm
import io.realm.exceptions.RealmException
import net.danlew.android.joda.JodaTimeAndroid
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainApplication : Application() {

    private var context: Context? = null
    private var pre: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        pre = getSharedPreferences(AppConstants.PREF_FILE_NAME, MODE_PRIVATE)
        Realm.init(context)
        JodaTimeAndroid.init(context)
        val isFirst: Boolean = pre!!.getBoolean(AppConstants.PREF_NAME_IS_FIRST, true)
        if (isFirst) {
            createDatabase()
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, AppConstants.LANG_CODE_EN))
    }

    @SuppressLint("CommitPrefEdits")
    fun createDatabase() {
        val raw: InputStream = resources.openRawResource(R.raw.database)
        val rd: BufferedReader = BufferedReader(InputStreamReader(raw))
        val db: Database = Gson().fromJson(rd, Database::class.java)
        try {
            val realm: Realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(db.jars)
            realm.copyToRealmOrUpdate(db.revenueTypes)
            realm.copyToRealmOrUpdate(db.expenditureTypes)
            realm.copyToRealmOrUpdate(db.revenues)
            realm.copyToRealmOrUpdate(db.expenditures)
            realm.commitTransaction()
            realm.close()
            editor = pre!!.edit()
            editor!!.putBoolean(AppConstants.PREF_NAME_IS_FIRST, false)
            editor!!.apply()
        } catch (e: RealmException) {
            e.printStackTrace()
        }
    }
}