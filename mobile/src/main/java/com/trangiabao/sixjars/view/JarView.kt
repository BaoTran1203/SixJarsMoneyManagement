package com.trangiabao.sixjars.view

import com.trangiabao.sixjars.model.Jar
import io.realm.RealmResults

interface JarView {

    fun onJarLoaded(jars: RealmResults<Jar>)
    fun onJarUpdateSucessed(result: Boolean)
}