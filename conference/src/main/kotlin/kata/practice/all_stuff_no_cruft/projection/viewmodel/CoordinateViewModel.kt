package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Coordinate


data class CoordinateViewModel(
    val latitude: Double,
    val longitude: Double
){
    constructor(coordinates: Coordinate) : this(
        latitude = coordinates.latitude,
        longitude = coordinates.longitude
    )
}

