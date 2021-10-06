package com.example.travelbus.views.adapter.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.BottomSheetUpiAdapter
import kotlinx.android.synthetic.main.fragment_payment.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager


class PaymentFragment : Fragment(R.layout.fragment_payment) {
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cvUpi.setOnClickListener {
            val bottomSheet = BottomSheetUpiAdapter()
            activity?.supportFragmentManager?.let { it1 -> bottomSheet.show(it1, "ModalBottomSheet") }
        }
    }
}