package kata.practice.all_stuff_no_cruft.jwt

class IssueToken(
    val tokenType: String,
    val accessToken: String,
    val expiresIn: Long,
    val refreshToken: String,
    val refreshExpiresIn: Long
)