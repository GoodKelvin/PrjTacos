package com.kelvingabe.kelvinoguno.prjtacos.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {
    val prefs_filename = "zanga_preferences"
    val prefs: SharedPreferences = context.getSharedPreferences(prefs_filename, 0)

    //User preferences
    private val is_logged_in = "isLoggedIn"
    private val user_email = "userEmail"
    private val user_id = "userID"

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(is_logged_in, false)
        set(value) = prefs.edit().putBoolean(is_logged_in, value).apply()

    var userEmail: String?
        get() = prefs.getString(user_email, "")
        set(value) = prefs.edit().putString(user_email, value).apply()

    var userID: String?
        get() = prefs.getString(user_id, "")
        set(value) = prefs.edit().putString(user_id, value).apply()
    //val requestQueue = Volley.newRequestQueue(context)
}