package kata.practice.all_stuff_no_cruft.application.command

data class UpdateCapacity(
    val conferenceId: Long,
    val maxCapacity: Int,
)