package com.tmw.incomeexpense.ui.other

import android.content.Context

val PREFERENCE_NAME = "IncomeExpense"
val PREFERENCE_LANGUAGE = "Language"

class MyPreference(context : Context){


    val preference = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

    fun getLoginCount() : String{
        return preference.getString(PREFERENCE_LANGUAGE,"en").toString()
    }

    fun setLoginCount(Language:String){
        val editor = preference.edit()
        editor.putString(PREFERENCE_LANGUAGE,Language)
        editor.apply()
    }

}