package com.example.geoquiz

import android.content.Context


class SharedPrefUtil(contex: Context) {
    companion object{
        private const val IS_DARK_MODE = "isDarkMode"
        private const val IS_ENG_MODE = "isEngMode"
    }
    private val sharedPref = contex.getSharedPreferences(
        contex.getString(R.string.app_name), Context.MODE_PRIVATE
    )
    private val editor = sharedPref.edit()
    fun setDarkMode(isDarkMode: Boolean){
        editor.putBoolean(IS_DARK_MODE,isDarkMode)
        editor.apply()
    }
    fun setEngMode(isEngMode: Boolean){
        editor.putBoolean(IS_ENG_MODE,isEngMode)
        editor.apply()
    }
    fun isDarkMode()= sharedPref.getBoolean(IS_DARK_MODE,false)
    fun isEngMode()= sharedPref.getBoolean(IS_ENG_MODE,false)
}