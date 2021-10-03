package com.example.travelbus.views.adapter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelbus.R
import com.example.travelbus.views.adapter.base.BaseActivity
import com.example.travelbus.views.adapter.fragments.bus_hire.BusHireWelcomeFragment

class BusHireActivity : BaseActivity() {
    override fun setupView(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().replace(R.id.busHireContainer, BusHireWelcomeFragment()).commit()
    }

    override fun provideLayoutId(): Int = R.layout.activity_bus_hire

}