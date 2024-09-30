package kata.practice.all_stuff_no_cruft.application.command

data class AddTalk(
    val conferenceId: Long,
    val speakerId: Long,
    val name: String,
    val description: String,
)