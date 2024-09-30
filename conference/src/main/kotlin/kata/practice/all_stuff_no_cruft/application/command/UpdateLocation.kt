package kata.practice.all_stuff_no_cruft.application.command

data class UpdateLocation(
    val conferenceId: Long,
    val city: String,
    val street: String,
    val zipCode: String,
    val latitude: Double,
    val longitude: Double
)