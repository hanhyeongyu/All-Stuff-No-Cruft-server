package kata.practice.all_stuff_no_cruft.speaker

class SpeakerRequests {
    data class IssueTokenRequest(
        val email: String,
        val password: String
    )

    data class RefreshToken(
        val refreshToken: String
    )
}