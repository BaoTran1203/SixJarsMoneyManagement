package com.trangiabao.sixjars.data.database

import com.trangiabao.sixjars.data.model.Wallet
import io.realm.Realm
import io.realm.exceptions.RealmException

object WalletDB {

    fun getAll(): List<Wallet> {
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val wallets: MutableList<Wallet> = mutableListOf()
            val jars = JarDB.getAll()
            for (jar in jars) {
                var totalRevenue: Double = RevenueDB.getAll().sumByDouble { it -> it.amount!! }
                totalRevenue = totalRevenue * jar.percent!! / 100.0
                val totalExpan = ExpenditureDB.getAll()
                        .filter { it -> it.jar!!.name == jar.name }
                        .sumByDouble { it -> it.amount!! }

                val wallet = Wallet()
                wallet.jar = jar
                wallet.totalRevenue = totalRevenue
                wallet.totalExpenditure = totalExpan
                wallet.currentAmout = totalRevenue - totalExpan
                wallets.add(wallet)
            }
            return wallets
        } catch (e: RealmException) {
            e.printStackTrace()
        } finally {
            realm.close()
        }
        return listOf()
    }
}