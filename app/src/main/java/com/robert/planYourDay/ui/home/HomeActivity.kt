package com.robert.planYourDay.ui.home

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.robert.planYourDay.R
import com.robert.planYourDay.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = "HomeActivity"


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

       binding.bottomNavigation.setupWithNavController(homeNavHostFragment.findNavController())

    }
}

