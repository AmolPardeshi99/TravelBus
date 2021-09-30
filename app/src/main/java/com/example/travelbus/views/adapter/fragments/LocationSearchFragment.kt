package com.example.travelbus.views.adapter.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelbus.R
import com.example.travelbus.views.adapter.adapters.BoardingListAdapter
import com.example.travelbus.views.adapter.adapters.CityListAdapter
import com.example.travelbus.views.adapter.adapters.PlacesAdapter
import com.google.android.libraries.places.api.Places
import kotlinx.android.synthetic.main.fragment_location_search.*

class LocationSearchFragment(val clickListener: PlacesAdapter.ClickListener) :
    Fragment(R.layout.fragment_location_search) {

    private val cityList = ArrayList<String>()
    private val boardingList = ArrayList<BoardingPoint>()
    private val recentList = ArrayList<String>()

    private lateinit var adapter: PlacesAdapter
    private var sourceDestinationFlag : Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            sourceDestinationFlag = getInt("flag")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nearbyCityList()
        nearbyBoardingList()
        recentList()
        searchAutoSuggestList()
        btnBack.setOnClickListener {
            onDetach()
        }
    }


    private fun searchAutoSuggestList() {
        Places.initialize(activity as Context, resources.getString(R.string.API_KEY))
        etSearch.addTextChangedListener(locationTextWatcher)
        val linearLayoutManager = LinearLayoutManager(activity as Context)
        adapter = PlacesAdapter(activity as Context, clickListener,sourceDestinationFlag!!)
        recyclerViewSearchBoarding.adapter = adapter
        recyclerViewSearchBoarding.layoutManager = linearLayoutManager
        adapter.notifyDataSetChanged()
    }

    private val locationTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.toString().length > 2 && s.toString() != "") {
                adapter.filter.filter(s.toString())
                if (recyclerViewSearchBoarding.visibility == View.GONE) {
                    recyclerViewSearchBoarding.visibility = View.VISIBLE
                } else {

                }
            } else {
                if (recyclerViewSearchBoarding.visibility == View.VISIBLE) {
                    recyclerViewSearchBoarding.visibility = View.GONE
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    private fun nearbyCityList() {
        cityList.add("Mumbai")
        cityList.add("Pune")
        cityList.add("Hyderabad")
        cityList.add("Bangalore")
        cityList.add("Chennai")
        val cityListAdapter = CityListAdapter(cityList,clickListener, sourceDestinationFlag!!)
        val linearLayoutManager1 = LinearLayoutManager(activity)
        recyclerViewCities.adapter = cityListAdapter
        recyclerViewCities.layoutManager = linearLayoutManager1
    }

    private fun nearbyBoardingList() {
        boardingList.add(BoardingPoint("Shivaji Nagar", "Pune"))
        boardingList.add(BoardingPoint("Swargate", "Pune"))
        boardingList.add(BoardingPoint("Railway Station", "Pune"))

        val boardingListAdapter = BoardingListAdapter(boardingList, clickListener, sourceDestinationFlag!!)
        val linearLayoutManager3 = LinearLayoutManager(activity)
        recyclerViewBoarding.adapter = boardingListAdapter
        recyclerViewBoarding.layoutManager = linearLayoutManager3

    }

    private fun recentList() {
        recentList.add("Pune")
        recentList.add("Delhi")
        val cityListAdapter = CityListAdapter(recentList, clickListener, sourceDestinationFlag!!)
        val linearLayoutManager2 = LinearLayoutManager(activity)
        recyclerViewRecent.adapter = cityListAdapter
        recyclerViewRecent.layoutManager = linearLayoutManager2
    }


}

class BoardingPoint(val area: String, val address: String) {
}