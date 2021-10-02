package com.example.travelbus.views.adapter.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import com.example.travelbus.views.adapter.activities.PaymentSuccessActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_phonepay_layout.view.btnPay

class BottomSheetPhonePay : BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view1 = inflater.inflate(R.layout.bottom_phonepay_layout, container, false)
        view1.btnPay.setOnClickListener {
            Toast.makeText(context, "payment done", Toast.LENGTH_LONG).show()
            startActivity(Intent(context, PaymentSuccessActivity::class.java))
        }
        return view1;
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

}