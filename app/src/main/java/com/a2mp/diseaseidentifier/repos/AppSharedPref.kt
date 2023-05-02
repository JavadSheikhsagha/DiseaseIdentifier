package com.a2mp.diseaseidentifier.repos

import android.content.Context

object AppSharedPref {

    fun getIsPurchased(context: Context) : Boolean {

        return context.getSharedPreferences("diseaseapp",Context.MODE_PRIVATE).getBoolean("is_purchased", false)
    }

    fun setIsPurchased(context: Context, boolean: Boolean) {
        context.getSharedPreferences("diseaseapp",Context.MODE_PRIVATE).edit().putBoolean("is_purchased",boolean).apply()
    }

}