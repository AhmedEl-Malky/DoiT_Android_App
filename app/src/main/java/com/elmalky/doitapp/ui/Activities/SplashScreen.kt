package com.elmalky.doitapp.ui.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.elmalky.doitapp.R
import com.elmalky.doitapp.models.dataBase.databases.AppDatabase
import com.elmalky.doitapp.util.Constants

class SplashScreen : AppCompatActivity() {
    lateinit var appSP: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        storeInSP()
        val firstTime = appSP.getBoolean(Constants.Names.FIRST_START, false)
        buildDataBase()
        Handler().postDelayed({
            if (firstTime) {
                startActivity(Intent(this, WalkThroughActivity::class.java))
                appSP.edit().putBoolean(Constants.Names.FIRST_START, false).apply()
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, 1700)
    }

    private fun buildDataBase() {
        AppDatabase.getInstanceByContext(applicationContext)
    }

    private fun storeInSP() {
        appSP = getSharedPreferences(Constants.Names.SP_NAME, Context.MODE_PRIVATE)
        appSP.edit().putBoolean(Constants.Names.FIRST_START, true).apply()
    }
}
