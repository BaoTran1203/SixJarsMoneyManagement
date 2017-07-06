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

@Suppress("unused")
class MainApplication : Application() {

    private val PREF_FILE_NAME: String = "Save"
    private var context: Context? = null
    private var pre: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    var Context: Context?
        get() = this.context
        set(value) {
            this.context = value
        }

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
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val raw: InputStream = resources.openRawResource(R.raw.default_database)
        val rd: BufferedReader = BufferedReader(InputStreamReader(raw))
        val gson: Gson = Gson()
        val db: Database = gson.fromJson(rd, Database::class.java)
        realm.copyToRealmOrUpdate(db.jars)
        realm.copyToRealmOrUpdate(db.expenditureTypes)
        realm.copyToRealmOrUpdate(db.revenueTypes)
        realm.copyToRealmOrUpdate(db.wallets)
        realm.copyToRealmOrUpdate(db.revenues)
        realm.copyToRealmOrUpdate(db.expenditures)
        realm.commitTransaction()
        editor = pre!!.edit()
        editor!!.putBoolean("isFirst", false)
        editor!!.apply()
    }

    fun restoreDatabase() {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val raw: InputStream = resources.openRawResource(R.raw.database)
        val rd: BufferedReader = BufferedReader(InputStreamReader(raw))
        val gson: Gson = Gson()
        val db: Database = gson.fromJson(rd, Database::class.java)
        realm.copyToRealmOrUpdate(db.jars)
        realm.copyToRealmOrUpdate(db.expenditureTypes)
        realm.copyToRealmOrUpdate(db.revenueTypes)
        realm.copyToRealmOrUpdate(db.wallets)
        realm.copyToRealmOrUpdate(db.revenues)
        realm.copyToRealmOrUpdate(db.expenditures)
        realm.commitTransaction()
    }
}