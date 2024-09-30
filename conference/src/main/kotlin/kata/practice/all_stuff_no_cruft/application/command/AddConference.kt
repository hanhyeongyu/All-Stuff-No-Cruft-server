package kata.practice.all_stuff_no_cruft.application.command

import java.time.LocalDateTime


data class AddConference(
    val conference: Conference,
    val information: Information,
    val schedule: Schedule,
    val address: Address,
){
    data class Conference(
        val name: String,
        val amount: Long,
        val maxCapacity: Int,
    )

    data class Information(
        val content: String,
        val contentType: String
    )

    data class Schedule(
        val meetingStartAt: LocalDateTime,
        val meetingEndAt: LocalDateTime,
        val registerStartAt: LocalDateTime,
        val registerEndAt: LocalDateTime,
    )

    data class Address(
        val city: String,
        val street: String,
        val zipCode: String,
        val coordinate: Coordinate
    )


    data class Coordinate(
        val latitude: Double,
        val longitude: Double
    )
}