package com.example.travelbus.views.adapter.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelbus.R
import kotlinx.android.synthetic.main.activity_payment_success.*

class PaymentSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        val path = "android.resource://com.example.travelbus/" + R.raw.payment_success
        val uri = Uri.parse(path)
        videoView.setVideoURI(uri)

        videoView.setOnPreparedListener { mp -> mp.start() }

        videoView.setOnCompletionListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        tvBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}