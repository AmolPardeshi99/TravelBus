package com.example.travelbus.views.adapter.activities

import android.content.Intent
import android.os.Bundle
import com.example.travelbus.R
import com.example.travelbus.views.adapter.base.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity


class SplashScreenActivity : BaseActivity() {
    private lateinit var auth: FirebaseAuth

    override fun provideLayoutId(): Int = R.layout.activity_splash_screen

    override fun setupView(savedInstanceState: Bundle?) {
        auth = Firebase.auth

        var flag = CoroutineScope(Dispatchers.Main).async {
            delay(2000)

            if (auth.currentUser != null) {
                Intent()
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(applicationContext, OnBoardingActivity::class.java)
                startActivity(intent)
            }
            finish()
        }


    }

}


//  CoroutineScope(Dispatchers.Main).launch {
//            delay(2000)
//            startActivity<OnBoardingActivity>()
//
//
//            if (auth.currentUser != null) {
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//            } else {
//                val intent = Intent(this, OnBoardingActivity::class.java)
//                startActivity(intent)
//            }
//            finish()
//        }