package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Embeddable


@Embeddable
data class Coordinate(
    val latitude: Double,
    val longitude: Double
)