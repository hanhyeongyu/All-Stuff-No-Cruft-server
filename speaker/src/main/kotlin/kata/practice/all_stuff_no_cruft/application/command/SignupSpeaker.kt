package kata.practice.all_stuff_no_cruft.application.command

data class SignupSpeaker(
    val email: String,
    val password: String,
    val name: String,
    val introduce: String
)