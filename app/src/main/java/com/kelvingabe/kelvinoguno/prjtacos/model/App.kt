package com.kelvingabe.kelvinoguno.prjtacos.model

import android.app.Application
import com.kelvingabe.kelvinoguno.prjtacos.util.FirebaseService
import com.kelvingabe.kelvinoguno.prjtacos.util.SharedPrefs
import com.kelvingabe.kelvinoguno.prjtacos.util.Utils

class App : Application() {
    companion object {
        lateinit var prefs: SharedPrefs
        lateinit var utils: Utils
        lateinit var fbService: FirebaseService
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        utils = Utils(applicationContext)
        fbService = FirebaseService(applicationContext)
        super.onCreate()
    }
}