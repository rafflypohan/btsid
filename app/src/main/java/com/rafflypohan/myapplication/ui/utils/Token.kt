package com.rafflypohan.myapplication.ui.utils

import android.content.Context

class TokenPreference(context: Context){
    private var prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun setToken(token: String){
        prefs.edit().putString(PREFS_VALUE, token).apply()
    }

    fun getToken(): String = prefs.getString(PREFS_VALUE, "")!!

    companion object {
        const val PREFS = "prefs"
        const val PREFS_VALUE = "prefs_value"
    }
}