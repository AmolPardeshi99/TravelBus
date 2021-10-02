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
import com.example.travelbus.views.adapter.adapters.OnBardingTabClicked
import kotlinx.android.synthetic.main.fragment_boarding_dropping.*

class BoardingDroppingFragment : Fragment(R.layout.fragment_boarding_dropping),OnBardingTabClicked {

    private var borDroppingList = ArrayList<BoardingDropping>()
    private lateinit var boardingAdapter: BoardingDroppingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        boardingAdapter = BoardingDroppingAdapter(borDroppingList,this)
        setRecyclerview()

        constraintLayout.setOnClickListener{
            onTabClicked()

        }
    }

    private fun setRecyclerview() {
        recyclerBoardingDroppingList.adapter = boardingAdapter
    }

    private fun setData() {
        var boardingDropping = BoardingDropping()
        boardingDropping.mobileNo = "8888884526"
        boardingDropping.subTitle = "Station Road- Oppo:- ST Stand"
        boardingDropping.time = "15:00"
        boardingDropping.title = "Jaipur Railway Station"
        borDroppingList.add(boardingDropping)

    }

    override fun onTabClicked() {
        viewHoriZontal1.visibility= View.INVISIBLE
        viewHoriZontal2.visibility = View.VISIBLE

    }

}