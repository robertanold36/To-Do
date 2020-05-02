package com.robert.planYourDay.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robert.planYourDay.R
import com.robert.planYourDay.ui.home.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            val intent=Intent(this@SplashScreen,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
