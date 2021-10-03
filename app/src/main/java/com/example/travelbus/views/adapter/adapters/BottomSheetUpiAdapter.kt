package com.example.travelbus.views.adapter.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.travelbus.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_upi_layout.view.*

class BottomSheetUpiAdapter: BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view1 = inflater.inflate(R.layout.bottom_upi_layout, container, false)

        view1.btnPay.setOnClickListener {
            val bottomSheet = BottomSheetPhonePay()
            activity?.supportFragmentManager?.let { it1 -> bottomSheet.show(it1, "ModalBottomSheet") }
            dismiss()
        }

        return  view1;
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}