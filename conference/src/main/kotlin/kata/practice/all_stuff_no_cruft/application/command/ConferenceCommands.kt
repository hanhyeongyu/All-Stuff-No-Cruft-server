package kata.practice.all_stuff_no_cruft.application.command

import java.time.LocalDateTime

class ConferenceCommands {
    data class AddConference(
        val conference: Conference,
        val information: Information,
        val location: Location,
        val ticket: Ticket
    ){
        data class Conference(
            val name: String,
            val period: Pair<LocalDateTime, LocalDateTime>,
        )

        data class Information(
            val content: String,
        )

        data class Location(
            val city: String,
            val street: String,
            val zipCode: String,
            val latitude: Double,
            val longitude: Double
        )

        data class Ticket(
            val totalCapacity: Int,
            val registration: Pair<LocalDateTime, LocalDateTime>,
            val price: Long
        )
    }
}