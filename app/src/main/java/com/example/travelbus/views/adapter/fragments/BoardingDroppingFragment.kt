package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelbus.R
import com.example.travelbus.models.local.BoardingDropping
import com.example.travelbus.views.adapter.adapters.BoardingDroppingAdapter
import kotlinx.android.synthetic.main.fragment_boarding_dropping.*

class BoardingDroppingFragment : Fragment(R.layout.fragment_boarding_dropping) {

    private var borDroppingList = ArrayList<BoardingDropping>()
    private lateinit var boardingAdapter: BoardingDroppingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        boardingAdapter = BoardingDroppingAdapter(borDroppingList)
        setRecyclerview()
    }

    private fun setRecyclerview() {
        recyclerBoardingDroppingList.adapter = boardingAdapter
    }

    private fun setData() {

    }

}