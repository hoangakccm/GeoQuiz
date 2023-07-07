package com.example.geoquiz

import android.annotation.SuppressLint
import android.app.UiModeManager.MODE_NIGHT_NO
import android.app.UiModeManager.MODE_NIGHT_YES
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate

class SettingActivity : AppCompatActivity() {
    private lateinit var darkmode: RadioButton
    private lateinit var lightmode: RadioButton
    private lateinit var displayMode: RadioGroup
    private lateinit var languageMode: RadioGroup
    private lateinit var sharedPrefUtil: SharedPrefUtil
    val defaultLanguage = "en"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initShareedPref()
        initViews()
        initUIEvenHandlers()

    }


    companion object  {
        fun getIntent(context: Context)=Intent(context, SettingActivity::class.java)
    }


    private fun initUIEvenHandlers() {
        val currentNightMode = AppCompatDelegate.getDefaultNightMode()
        when (currentNightMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> darkmode.setChecked(true)
            AppCompatDelegate.MODE_NIGHT_NO -> lightmode.setChecked(true)
        }
        displayMode.setOnCheckedChangeListener { _, checkedId ->
            var isDarkMode = false
            when (checkedId) {
                R.id.darkmode -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    isDarkMode = true
                }
                R.id.lightmode -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
            sharedPrefUtil.setDarkMode(isDarkMode)
        }
        languageMode.setOnCheckedChangeListener { _, checkedId ->
            var isEngMode = false
            when (checkedId) {
                R.id.Viemode -> {
                    "vi"
                    isEngMode = false
                }
                R.id.Engmode -> {
                    "en"
                    isEngMode = true
                }
            }
            sharedPrefUtil.setEngMode(isEngMode)
        }

    }

    private fun initShareedPref() {
        sharedPrefUtil = SharedPrefUtil(this)
    }

    private fun initViews(){
        displayMode = findViewById(R.id.displayMode)
        lightmode = findViewById(R.id.lightmode)
        darkmode = findViewById(R.id.darkmode)
        languageMode = findViewById(R.id.languageMode)
    }

}