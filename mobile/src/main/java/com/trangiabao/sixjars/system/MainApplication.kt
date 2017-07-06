package com.trangiabao.sixjars.system

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.model.Database
import io.realm.Realm
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class MainApplication : Application() {

    private val PREF_FILE_NAME: String = "Save"
    private var context: Context? = null
    private var pre: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        pre = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE)
        val isFirst: Boolean = pre!!.getBoolean("isFirst", true)
        if (isFirst) {
            createDefaultDatabase()
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
    }

    @SuppressLint("CommitPrefEdits")
    fun createDefaultDatabase() {
        val raw: InputStream = resources.openRawResource(R.raw.default_database)
        val rd: BufferedReader = BufferedReader(InputStreamReader(raw))
        val db: Database = Gson().fromJson(rd, Database::class.java)

        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealm(db.jars)

        for (type in db.expenditureTypes) {
            type.id = UUID.randomUUID().toString()
            realm.copyToRealm(type)
        }

        for (type in db.revenueTypes) {
            type.id = UUID.randomUUID().toString()
            realm.copyToRealm(type)
        }

        realm.copyToRealm(db.wallets)
        realm.copyToRealm(db.revenues)
        realm.copyToRealm(db.expenditures)
        realm.commitTransaction()
        realm.close()
        editor = pre!!.edit()
        editor!!.putBoolean("isFirst", false)
        editor!!.apply()
    }
}