package com.trangiabao.sixjars.presenter

import android.content.Context
import com.trangiabao.sixjars.model.Jar
import com.trangiabao.sixjars.view.JarView
import io.realm.Realm
import io.realm.RealmResults

class JarPresenter(private var context: Context, private var view: JarView) : JarPresenterImpl {

    override fun getAllJar() {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        view.onJarLoaded(realm.where(Jar::class.java).findAll())
    }

    override fun updateJar(ratios: ArrayList<Int>) {
        if (ratios.sum() == 100) {
            Realm.init(context)
            val realm = Realm.getDefaultInstance()
            val jars: RealmResults<Jar> = realm.where(Jar::class.java).findAll()
            realm.beginTransaction()
            var i = 0
            for (jar in jars) {
                jar.percent = ratios[i++]
            }
            realm.commitTransaction()
            view.onJarUpdateSucessed(true)
        } else
            view.onJarUpdateSucessed(false)
    }
}