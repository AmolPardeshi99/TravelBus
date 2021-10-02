package com.example.travelbus.models.local

class Buses(
    var id:String="",
    var name: String = "",
    var description: String = "",
    var from: String = "",
    var to: String = "",
    var seats: ArrayList<Seats> = ArrayList<Seats>(),
    var price: String = "",
    var travellingtime: String = "",
    var timing: String = ""
    ) {

}