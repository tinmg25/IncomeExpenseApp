package com.tmw.incomeexpense

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LocaleManager {
    fun setLocale(mContext: Context):Context{
        return if(App.instance!!.getLanguagePref()!=null)
            updateResources(mContext,App.instance!!.getLanguagePref()!!)
        else
            mContext
    }

    fun setNewLocale(mContext: Context,language:String):Context{
        App.instance!!.setLanguagePref(language)
        return updateResources(mContext,language)
    }

    private fun updateResources(context: Context, language: String): Context {
        var localContext=context
        var locale= Locale(language)
        Locale.setDefault(locale)
        val res=context.resources
        val config=Configuration(res.configuration)
        config.setLocale(locale)
        localContext=context.createConfigurationContext(config)
        return localContext
    }
}