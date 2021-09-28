package com.example.travelbus.views.adapter.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.travelbus.R
import com.example.travelbus.views.adapter.fragments.Country_LanguageFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            startActivity<OnBoardingActivity>()
            finish()
        }
    }

}