package com.example.travelbus.views.adapter.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import com.example.travelbus.views.adapter.activities.PaymentSuccessActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_phonepay_layout.view.btnPay

class SheetSeatInfo : DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view1 = inflater.inflate(R.layout.seat_info_layout, container, false)
        return view1;
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

}