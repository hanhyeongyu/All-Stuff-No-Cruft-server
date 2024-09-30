package kata.practice.all_stuff_no_cruft.application.command




data class UpdateInformation(
    val conferenceId: Long,
    val content: String,
    val contentType: String
)