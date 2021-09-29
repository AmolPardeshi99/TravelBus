package com.example.travelbus.views.adapter.activities

import android.os.Bundle
import com.example.travelbus.R
import com.example.travelbus.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity


class SplashScreenActivity : BaseActivity() {
    override fun provideLayoutId(): Int = R.layout.activity_splash_screen

    override fun setupView(savedInstanceState: Bundle?) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            startActivity<OnBoardingActivity>()
            finish()
        }
    }

}