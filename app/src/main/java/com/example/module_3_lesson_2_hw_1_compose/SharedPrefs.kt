package com.example.module_3_lesson_2_hw_1_compose

import android.content.Context

class SharedPrefs(context: Context) {
    private val LANGUAGE = "language"
    private val LANGUAGESWITCH = "language switch"
    private val USERNAME = "username"
    private val GRAPHICS = "graphics"
    private val GRAPHICSSWITCH = "graphics switch"
    private val AUTOSAVE = "autosave"
    private val AUTOSAVESWITCH = "autosave switch"
    private val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    fun setLanguage(language: String){
        prefs.edit().putString(LANGUAGE, language).apply()
    }
    fun getLanguage(): String {
        val language: String =
            if (prefs.getString(LANGUAGE, "ENG") != null){
                prefs.getString(LANGUAGE, "ENG")!!
            } else "ENG"
        return language
    }

    fun setLanguageSwitch(languageSwitch: Boolean){
        prefs.edit().putBoolean(LANGUAGESWITCH, languageSwitch).apply()
    }
    fun getLanguageSwitch(): Boolean {
        return prefs.getBoolean(LANGUAGESWITCH, false)
    }

    fun setGraphics(graphics: String){
        prefs.edit().putString(GRAPHICS, graphics).apply()
    }
    fun getGraphics(): String {
        val graphics: String =
            if (prefs.getString(GRAPHICS, "Low") != null){
                prefs.getString(GRAPHICS, "Low")!!
            } else "Low"
        return graphics
    }

    fun setGraphicsSwitch(graphicsSwitch: Boolean){
        prefs.edit().putBoolean(GRAPHICSSWITCH, graphicsSwitch).apply()
    }
    fun getGraphicsSwitch(): Boolean {
        return prefs.getBoolean(GRAPHICSSWITCH, false)
    }

    fun setAutosave(autosave: String){
        prefs.edit().putString(AUTOSAVE, autosave).apply()
    }
    fun getAutosave(): String {
        val autosave: String =
            if (prefs.getString(AUTOSAVE, "Off") != null){
                prefs.getString(AUTOSAVE, "Off")!!
            } else "Off"
        return autosave
    }

    fun setAutosaveSwitch(autosaveSwitch: Boolean){
        prefs.edit().putBoolean(AUTOSAVESWITCH, autosaveSwitch).apply()
    }
    fun getAutosaveSwitch(): Boolean {
        return prefs.getBoolean(AUTOSAVESWITCH, false)
    }

}