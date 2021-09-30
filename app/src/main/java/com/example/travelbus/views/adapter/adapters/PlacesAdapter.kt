package com.example.travelbus.views.adapter.adapters

import android.content.Context
import android.graphics.Typeface
import android.text.style.CharacterStyle
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.travelbus.R
import com.example.travelbus.views.adapter.fragments.BoardingPoint
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Tasks
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.*
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class PlacesAdapter(private val mContext: Context, val clickListener: ClickListener) :
    RecyclerView.Adapter<PlacesAdapter.PredictionHolder>(), Filterable {
    private var mResultList: ArrayList<PlaceAutocomplete>? = ArrayList()
    private val STYLE_BOLD: CharacterStyle
    private val STYLE_NORMAL: CharacterStyle
    private val placesClient: PlacesClient
    private var currentAddress = ""
    private var currentArea = ""
//    private var clickListener: ClickListener? = null


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PredictionHolder {
        val layoutInflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val convertView: View =
            layoutInflater.inflate(R.layout.boarding_point_item_layout, viewGroup, false)
        return PredictionHolder(convertView)
    }


    override fun onBindViewHolder(mPredictionHolder: PredictionHolder, i: Int) {
        val addressArray = mResultList!![i].address.toString().split(", ")
        if (addressArray.size < 4) {
            if (mPredictionHolder.address.visibility != View.GONE) {
                mPredictionHolder.address.visibility = View.GONE
                mPredictionHolder.boarding.visibility = View.GONE
            }
            mPredictionHolder.area.text = mResultList!![i].area
        }
        if (addressArray.size >= 4) {
            if (mPredictionHolder.address.visibility != View.VISIBLE) {
                mPredictionHolder.address.visibility = View.VISIBLE
                mPredictionHolder.boarding.visibility = View.VISIBLE
            }
            mPredictionHolder.address.text = addressArray[addressArray.size - 3]
            mPredictionHolder.area.text = mResultList!![i].area
        }

    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val results = FilterResults()
                if (constraint != null) {
                    mResultList = myPlaceData(constraint)
                    if (mResultList != null) {
                        results.values = mResultList
                        results.count = mResultList!!.size
                    }
                }
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged()
                }
            }
        }
    }

    private fun myPlaceData(constraint: CharSequence): ArrayList<PlaceAutocomplete> {
        val resultList = ArrayList<PlaceAutocomplete>()
        val token = AutocompleteSessionToken.newInstance()
        val request = FindAutocompletePredictionsRequest.builder()
            .setSessionToken(token)
            .setQuery(constraint.toString())
            .build()
        val autocompletePredictions = placesClient.findAutocompletePredictions(request)
        try {
            Tasks.await(autocompletePredictions, 60, TimeUnit.SECONDS)
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: TimeoutException) {
            e.printStackTrace()
        }
        return if (autocompletePredictions.isSuccessful) {
            val findAutocompletePredictionsResponse = autocompletePredictions.result
            if (findAutocompletePredictionsResponse != null) for (prediction in findAutocompletePredictionsResponse.autocompletePredictions) {
                resultList.add(
                    PlaceAutocomplete(
                        prediction.placeId,
                        prediction.getPrimaryText(STYLE_NORMAL).toString(),
                        prediction.getFullText(STYLE_BOLD).toString()
                    )
                )
            }
            resultList
        } else {
            resultList
        }
    }

    override fun getItemCount(): Int {
        return mResultList!!.size
    }

    fun getItem(position: Int): PlaceAutocomplete {
        return mResultList!![position]
    }

    inner class PredictionHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal val address: TextView
        internal val area: TextView
        internal val boarding: TextView
        private val mRow: ConstraintLayout
        private lateinit var item : PlaceAutocomplete
        override fun onClick(v: View) {
            item = mResultList!![adapterPosition]
            if (v.id == R.id.itemBoarding) {
                val placeId = item.placeId.toString()
                val placeFields = Arrays.asList(
                    Place.Field.ID,
                    Place.Field.NAME,
                    Place.Field.LAT_LNG,
                    Place.Field.ADDRESS
                )
                val request = FetchPlaceRequest.builder(placeId, placeFields).build()
                placesClient.fetchPlace(request).addOnSuccessListener { response ->
                    val place = response.place
//                    clickListener!!.click(place)
                }.addOnFailureListener { exception ->
                    if (exception is ApiException) {
                        Toast.makeText(mContext, exception.message + "", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        init {
            area = itemView.findViewById(R.id.tvBoardingPoint)
            address = itemView.findViewById(R.id.tvBoardingCity)
            boarding = itemView.findViewById(R.id.tvBoardAt)
            mRow = itemView.findViewById(R.id.itemBoarding)
            itemView.setOnClickListener(this)
            mRow.setOnClickListener {
                currentArea =""
                currentAddress =""
                item = mResultList!![adapterPosition]
                val addressArray = item.address.toString().split(", ")
                if (addressArray.size >= 4) {
                    currentAddress = addressArray[addressArray.size - 3]
                }
                currentArea = mResultList?.get(adapterPosition)?.area.toString()
                clickListener.click(currentArea, currentAddress)
            }
        }
    }

    inner class PlaceAutocomplete internal constructor(
        var placeId: CharSequence,
        var area: CharSequence,
        var address: CharSequence
    ) {
        override fun toString(): String {
            return area.toString()
        }
    }

//    fun setClickListener(clickListener: ClickListener?) {
//        this.clickListener = clickListener
//    }

    interface ClickListener {
        fun click(area: String?, address: String?)
        fun boardingItemClicked(boardingPoint: BoardingPoint?)
        fun cityItemClicked(city: String?)
        fun sourceSearchClicked()
        fun destinationSearchClicked()
    }

    init {
        STYLE_BOLD = StyleSpan(Typeface.BOLD)
        STYLE_NORMAL = StyleSpan(Typeface.NORMAL)
        placesClient = Places.createClient(mContext)
    }


}