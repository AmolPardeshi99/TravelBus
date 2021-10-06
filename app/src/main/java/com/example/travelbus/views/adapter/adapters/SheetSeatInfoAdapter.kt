package com.example.travelbus.views.adapter.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.travelbus.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_upi_layout.view.*

class SheetSeatInfoAdapter: DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view1 = inflater.inflate(R.layout.seat_info_layout, container, false)

        return  view1;
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}