package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded

@Embeddable
data class Address(
    val city: String,
    val street: String,
    val zipCode: String,
    @Embedded
    val coordinate: Coordinate
){


    constructor(city: String, street: String, zipCode: String, latitude: Double, longitude: Double) : this(
        city, street, zipCode, Coordinate(latitude, longitude)
    )
}