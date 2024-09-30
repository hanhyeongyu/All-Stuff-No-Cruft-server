package kata.practice.all_stuff_no_cruft.application.command


data class UpdatePrice(
    val conferenceId: Long,
    val amount: Long
)