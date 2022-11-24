package com.miguelmendoza.pac_desarrollo_m08_mendozamiguel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import java.util.*
import java.util.concurrent.Delayed

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_PAC_Desarrollo_M08_MendozaMiguel)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Paramos la ejecucion durante 2 segundos para poder apreciar el SplashScreen
        Thread.sleep(1000)

        // Navega a la Activity1
        val intent = Intent(this, Activity1::class.java)
        startActivity(intent)

        // Mata la vista para impedir volver a ella con el backbutton
        finish()
    }
}