package kata.practice.all_stuff_no_cruft.organizer

class OrganizerRequests {
    data class IssueTokenRequest(
        val email: String,
        val password: String
    )

    data class RefreshToken(
        val refreshToken: String
    )
}